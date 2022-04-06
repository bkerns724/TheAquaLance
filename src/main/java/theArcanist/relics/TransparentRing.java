package theArcanist.relics;

import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

// Code in ShadowcloakPower
public class TransparentRing extends AbstractArcanistRelic {
    public static final String ID = makeID(TransparentRing.class.getSimpleName());

    public TransparentRing() {
        super(ID, RelicTier.SPECIAL, LandingSound.MAGICAL, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        setUpdatedDescription();
    }
}
