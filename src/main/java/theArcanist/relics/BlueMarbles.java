package theArcanist.relics;

import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class BlueMarbles extends AbstractArcanistRelic {
    public static final String ID = makeID(BlueMarbles.class.getSimpleName());

    // code in damage types
    public BlueMarbles() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        setUpdatedDescription();
    }

    @Override
    public boolean canSpawn() {
        return !adp().hasRelic(ManaPurifier.ID);
    }
}
