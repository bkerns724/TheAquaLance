package theArcanist.relics;

import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class RandomRelic extends AbstractEasyRelic {
    public static final String ID = makeID("RandomRelic");

    public RandomRelic() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
    }
}
