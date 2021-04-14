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
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    @Override
    public boolean onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == adp())
            power.amount++;
        return true;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DEBUFF_AMOUNT + DESCRIPTIONS[1];
    }
}