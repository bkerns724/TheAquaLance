package theExile.relics;

import theExile.TheExile;

import static theExile.ExileMod.makeID;

public class Beastiary extends AbstractExileRelic {
    public static final String ID = makeID(Beastiary.class.getSimpleName());
    public static final float BONUS = 1f;

    public Beastiary() {
        super(ID, RelicTier.RARE, LandingSound.HEAVY, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }
}
