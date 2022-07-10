package theExile.relics;

import theExile.TheExile;

import static theExile.ExileMod.makeID;

public class PaperKat extends AbstractExileRelic {
    public static final String ID = makeID("PaperKat");

    // code in JinxPower
    public PaperKat() {
        super(ID, RelicTier.UNCOMMON,
                LandingSound.FLAT,
                TheExile.Enums.EXILE_BLARPLE_COLOR);
        setUpdatedDescription();
    }
}
