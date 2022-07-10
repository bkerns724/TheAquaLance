package theArcanist.relics;

import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

// Code in FrostbitePower
public class UnmeltingIce extends AbstractArcanistRelic {
    public static final String ID = makeID(UnmeltingIce.class.getSimpleName());
    public static final int FROST_BOOST = 2;

    public UnmeltingIce() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = FROST_BOOST;
        setUpdatedDescription();
    }
}