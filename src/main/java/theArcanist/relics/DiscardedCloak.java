package theArcanist.relics;

import theArcanist.TheArcanist;
import theArcanist.powers.ShadowcloakPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class DiscardedCloak extends AbstractArcanistRelic {
    public static final String ID = makeID("DiscardedCloak");
    public static final int CLOAK_PERCENT = 40;
    public static final float CLOAK_MULT = CLOAK_PERCENT/100.0f;

    //code in ShadowcloakPower
    public DiscardedCloak() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = CLOAK_PERCENT;
        amount2 = ShadowcloakPower.DAMAGE_MULT_DESC;
        setUpdatedDescription();
    }
}
