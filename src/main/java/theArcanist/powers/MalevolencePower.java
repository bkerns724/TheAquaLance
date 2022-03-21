package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class MalevolencePower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID("Malevolence");

    public MalevolencePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        isTwoAmount = true;
        amount2 = 1;
    }

    @Override
    public void stackPower(int stackAmount) {
        amount2 += 1;
        super.stackPower(stackAmount);
    }

    @Override
    public void onApplyPower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (pow.type == PowerType.DEBUFF && !pow.ID.equals("Shackled") && source == owner
                && target != owner && !target.hasPower("Artifact")) {
            flash();
            thornDmgTop(target, amount, ArcanistMod.Enums.BLOOD);
            amount += amount2;
        }
    }
}