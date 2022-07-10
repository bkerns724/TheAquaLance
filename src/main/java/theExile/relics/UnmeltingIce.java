package theExile.relics;

import theExile.TheExile;

import static theExile.ExileMod.makeID;

// Code in FrostbitePower
public class UnmeltingIce extends AbstractExileRelic {
    public static final String ID = makeID(UnmeltingIce.class.getSimpleName());
    public static final int FROST_BOOST = 2;

    public UnmeltingIce() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = FROST_BOOST;
        setUpdatedDescription();
    }
}