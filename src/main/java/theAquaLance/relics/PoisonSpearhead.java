package theAquaLance.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;

import static theAquaLance.util.Wiz.*;

public class PoisonSpearhead extends AbstractEasyRelic implements OnApplyPowerRelic {
    public static final String ID = makeID("PoisonSpearhead");
    private static final int DEBUFF_AMOUNT = 1;

    public PoisonSpearhead() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    @Override
    public boolean onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == adp() && power.type == AbstractPower.PowerType.DEBUFF) {
            if (power.amount > 0) {
                power.amount++;
                power.updateDescription();
            } else if (power.amount < 0 && power.canGoNegative) {
                power.amount--;
                power.updateDescription();
            }
        }
        return true;
    }

    @Override
    public int onApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (source == adp() && power.type == AbstractPower.PowerType.DEBUFF) {
            if (power.amount > 0)
                return stackAmount + 1;
            else if (power.amount < 0 && power.canGoNegative)
                return stackAmount - 1;
        }
        return stackAmount;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DEBUFF_AMOUNT + DESCRIPTIONS[1];
    }
}