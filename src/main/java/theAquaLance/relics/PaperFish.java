package theAquaLance.relics;

import theAquaLance.TheAquaLance;
import theAquaLance.powers.HobbledPower;
import theAquaLance.powers.SoakedPower;

import static theAquaLance.AquaLanceMod.makeID;

public class PaperFish extends AbstractEasyRelic {
    public static final String ID = makeID("PaperFish");
    public static final int SOAK_POWER = 75;

    public PaperFish() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + SOAK_POWER + DESCRIPTIONS[1] + SoakedPower.NORMAL_SOAK + DESCRIPTIONS[2];
    }
}