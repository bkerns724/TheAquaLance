package theAquaLance.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.TheAquaLance;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.AquaLanceMod.makeID;

import static theAquaLance.util.Wiz.*;

public class VialOfSwampWater extends AbstractEasyRelic implements OnApplyPowerRelic {
    public static final String ID = makeID("VialOfSwampWater");
    private static final int BLOCK_AMT = 2;

    public VialOfSwampWater() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    public boolean onApplyPower (AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == adp() && power.type == AbstractPower.PowerType.DEBUFF && power.ID != EmbedPower.POWER_ID)
            atb(new GainBlockAction(adp(), BLOCK_AMT));

        return true;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK_AMT + DESCRIPTIONS[1];
    }
}