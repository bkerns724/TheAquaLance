package theAquaLance.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.lwjgl.Sys;
import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;

import static theAquaLance.util.Wiz.*;

public class PoisonSpearhead extends AbstractEasyRelic implements OnApplyPowerRelic {
    public static final String ID = makeID("PoisonSpearhead");
    private static final int ADD_INCREASE = 1;
    private static final float MULT_INCREASE = 0.25F;
    private static final int MULT_FOR_STRING = 25;

    public PoisonSpearhead() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    @Override
    public boolean onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        /*
        if (source == adp() && power.type == AbstractPower.PowerType.DEBUFF && target != source) {
            if (power.amount > 0) {
                float increase = Math.max(ADD_INCREASE, power.amount * MULT_INCREASE);
                power.amount += increase;
                power.updateDescription();
            } else if (power.amount < 0 && power.canGoNegative) {
                float decrease = Math.min(-ADD_INCREASE, power.amount * MULT_INCREASE);
                power.amount -= decrease;
                power.updateDescription();
            }
        }
        */
        return true;
    }

    @Override
    public int onApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (source == adp() && power.type == AbstractPower.PowerType.DEBUFF && target != source) {
            if (stackAmount > 0) {
                float increase = Math.max(ADD_INCREASE, stackAmount * MULT_INCREASE);
                stackAmount += increase;
                power.updateDescription();
            } else if (stackAmount < 0 && power.canGoNegative) {
                float decrease = Math.min(-ADD_INCREASE, stackAmount * MULT_INCREASE);
                stackAmount -= decrease;
                power.updateDescription();
            }
        }
        return stackAmount;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + ADD_INCREASE + DESCRIPTIONS[1] + MULT_FOR_STRING + DESCRIPTIONS[2];
    }
}