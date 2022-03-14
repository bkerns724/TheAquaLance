package theArcanist.relics;

import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

// Code in ResonatingPower
public class TuningFork extends AbstractArcanistRelic {
    public static final String ID = makeID("TuningFork");

    public TuningFork() {
        super(ID, RelicTier.COMMON, LandingSound.MAGICAL, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
    }
}
