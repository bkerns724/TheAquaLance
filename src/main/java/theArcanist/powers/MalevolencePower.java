package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class MalevolencePower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID("Malevolence");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MalevolencePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
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
            thornDmg(target, amount, ArcanistMod.Enums.BLOOD);
            amount += amount2;
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}