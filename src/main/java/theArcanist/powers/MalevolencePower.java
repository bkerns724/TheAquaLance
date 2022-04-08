package theArcanist.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class MalevolencePower extends AbstractArcanistPower implements OnReceivePowerPower {
    public static String POWER_ID = ArcanistMod.makeID(MalevolencePower.class.getSimpleName());

    public MalevolencePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        isTwoAmount = true;
        amount2 = 1;
        updateDescription();
    }

    public MalevolencePower(AbstractCreature owner, int amount, int amount2) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        isTwoAmount = true;
        this.amount2 = amount2;
        updateDescription();
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (pow instanceof MalevolencePower && target == owner)
            amount2 += ((MalevolencePower) pow).amount2;
        return true;
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