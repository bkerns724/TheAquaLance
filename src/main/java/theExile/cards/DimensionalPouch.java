package theExile.cards;

import theExile.powers.DimensionalPouchPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class DimensionalPouch extends AbstractExileCard {
    public final static String ID = makeID(DimensionalPouch.class.getSimpleName());
    private final static int COST = 0;

    public DimensionalPouch() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = DimensionalPouchPower.CARD_DRAW;
    }

    public void nonTargetUse() {
        applyToSelf(new DimensionalPouchPower(1));
    }

    public void upp() {
        isInnate = true;
    }
}