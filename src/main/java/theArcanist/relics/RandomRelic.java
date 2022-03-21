package theArcanist.relics;

import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

public class RandomRelic extends AbstractArcanistRelic {
    public static final String ID = makeID(RandomRelic.class.getSimpleName());

    public RandomRelic() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        setUpdatedDescription();
    }
}
