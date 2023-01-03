package theExile.relics;

import theExile.TheExile;

import static theExile.ExileMod.makeID;

public class TuningFork extends AbstractExileRelic {
    public static final String ID = makeID(TuningFork.class.getSimpleName());
    public static final int DAMAGE_AMOUNT = 1;

    public TuningFork() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = DAMAGE_AMOUNT;
        setUpdatedDescription();
    }
}
