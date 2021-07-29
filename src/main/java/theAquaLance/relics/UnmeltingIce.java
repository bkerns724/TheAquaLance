package theAquaLance.relics;

import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;

//Code is in ApplyPowerAction patch
public class UnmeltingIce extends AbstractEasyRelic {
    public static final String ID = makeID("UnmeltingIce");
    private static final int FROST_BOOST = 1;

    public UnmeltingIce() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + FROST_BOOST + DESCRIPTIONS[1];
    }
}