package theArcanist.relics;

import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

public class PaperKat extends AbstractArcanistRelic {
    public static final String ID = makeID("PaperKat");

    // code in JinxPower
    public PaperKat() {
        super(ID, RelicTier.UNCOMMON,
                LandingSound.FLAT,
                TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        setUpdatedDescription();
    }
}
