package theAquaLance.relics;

import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;

public class Chains extends AbstractEasyRelic {
    public static final String ID = makeID("Chains");

    public Chains() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}