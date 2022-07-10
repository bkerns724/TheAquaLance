package theExile.relics;

import theExile.TheExile;

import static theExile.ExileMod.makeID;

// Code in ShadowcloakPower
public class TransparentBracelet extends AbstractExileRelic {
    public static final String ID = makeID(TransparentBracelet.class.getSimpleName());

    public TransparentBracelet() {
        super(ID, RelicTier.SPECIAL, LandingSound.MAGICAL, TheExile.Enums.EXILE_BLARPLE_COLOR);
        setUpdatedDescription();
    }
}
