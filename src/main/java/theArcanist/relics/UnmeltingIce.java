package theArcanist.relics;

import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

public class UnmeltingIce extends AbstractArcanistRelic {
    public static final String ID = makeID("UnmeltingIce");
    private static final int FROST_BOOST = 1;

    public UnmeltingIce() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + FROST_BOOST + DESCRIPTIONS[1];
    }
}