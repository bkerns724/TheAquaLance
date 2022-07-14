package theExile.relics;

import theExile.TheExile;

import static theExile.ExileMod.makeID;

public class ChemicalZ extends AbstractExileRelic {
    public static final String ID = makeID(ChemicalZ.class.getSimpleName());
    public static final int BOOST_AMOUNT = 2;

    // Code in AbstractExileCard
    public ChemicalZ() {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = BOOST_AMOUNT;
        setUpdatedDescription();
    }
}
