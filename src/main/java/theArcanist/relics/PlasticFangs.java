package theArcanist.relics;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

public class PlasticFangs extends AbstractArcanistRelic {
    public static final String ID = makeID(PlasticFangs.class.getSimpleName());
    public static final int TEMP_HP_AMOUNT = 2;

    // code in DarkDamage
    public PlasticFangs() {
        super(ID, RelicTier.UNCOMMON,
                LandingSound.HEAVY,
                TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = TEMP_HP_AMOUNT;
        setUpdatedDescription();
    }
}
