package theArcanist.relics;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

public class DarkFunnel extends AbstractArcanistRelic {
    public static final String ID = makeID("DarkFunnel");
    public static final int TEMP_HP_AMOUNT = 2;

    // code in DarkDamage
    public DarkFunnel() {
        super(ID, RelicTier.UNCOMMON,
                LandingSound.HEAVY,
                TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = TEMP_HP_AMOUNT;
        setUpdatedDescription();
    }
}
