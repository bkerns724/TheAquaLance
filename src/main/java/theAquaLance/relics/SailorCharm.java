package theAquaLance.relics;

import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;

import static theAquaLance.util.Wiz.*;

public class SailorCharm extends AbstractEasyRelic {
    public static final String ID = makeID("SailorCharm");
    private static final int DRAW_AMOUNT = 1;

    public SailorCharm() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    // code in AbstractWaveCard

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}