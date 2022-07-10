package theExile.relics;

import theExile.TheExile;

import static theExile.ExileMod.makeID;

// Code in Resonance
public class TuningFork extends AbstractExileRelic {
    public static final String ID = makeID(TuningFork.class.getSimpleName());
    public static final int BOOST_AMOUNT = 2;

    public TuningFork() {
        super(ID, RelicTier.COMMON, LandingSound.MAGICAL, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = BOOST_AMOUNT;
        setUpdatedDescription();
    }
}
