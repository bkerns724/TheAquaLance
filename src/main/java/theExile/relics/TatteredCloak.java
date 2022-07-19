package theExile.relics;

import theExile.TheExile;
import theExile.powers.ShadowcloakPower;

import static theExile.ExileMod.makeID;

public class TatteredCloak extends AbstractExileRelic {
    public static final String ID = makeID("TatteredCloak");
    public static final int CLOAK_PERCENT = 35;
    public static final float CLOAK_MULT = CLOAK_PERCENT/100.0f;

    //code in ShadowcloakPower
    public TatteredCloak() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = CLOAK_PERCENT;
        amount2 = ShadowcloakPower.DAMAGE_MULT_DESC;
        setUpdatedDescription();
    }
}
