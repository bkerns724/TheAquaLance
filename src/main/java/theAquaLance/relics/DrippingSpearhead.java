package theAquaLance.relics;

import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;

public class DrippingSpearhead extends AbstractEasyRelic {
    public static final String ID = makeID("DrippingSpearhead");
    private static final int DROWN_AMT = 1;

    public DrippingSpearhead() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, TheAquaLance.Enums.TURQUOISE_COLOR);
    }
    
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DROWN_AMT + DESCRIPTIONS[1];
    }
}