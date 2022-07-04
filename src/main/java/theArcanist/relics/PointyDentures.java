package theArcanist.relics;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

public class PointyDentures extends AbstractArcanistRelic {
    public static final String ID = makeID(PointyDentures.class.getSimpleName());
    public static final int TEMP_HP_AMOUNT = 2;

    // code in DarkDamage
    public PointyDentures() {
        super(ID, RelicTier.UNCOMMON,
                LandingSound.HEAVY,
                TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = TEMP_HP_AMOUNT;
        setUpdatedDescription();
    }
}
