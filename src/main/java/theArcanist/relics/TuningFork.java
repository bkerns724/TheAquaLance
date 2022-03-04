package theArcanist.relics;

import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

// Code in ResonatingPower
public class TuningFork extends AbstractEasyRelic {
    public static final String ID = makeID("TuningFork");

    public TuningFork() {
        super(ID, RelicTier.COMMON, LandingSound.MAGICAL, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
    }
}
