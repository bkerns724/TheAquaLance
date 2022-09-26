package theExile.relics;

import theExile.TheExile;

import static theExile.ExileMod.makeID;

public class BlueMarbles extends AbstractExileRelic {
    public static final String ID = makeID(BlueMarbles.class.getSimpleName());
    public static final float MULT = 2.0f;

    public BlueMarbles() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }
}
