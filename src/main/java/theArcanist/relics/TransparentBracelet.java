package theArcanist.relics;

import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

// Code in ShadowcloakPower
public class TransparentBracelet extends AbstractArcanistRelic {
    public static final String ID = makeID(TransparentBracelet.class.getSimpleName());

    public TransparentBracelet() {
        super(ID, RelicTier.SPECIAL, LandingSound.MAGICAL, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        setUpdatedDescription();
    }
}
