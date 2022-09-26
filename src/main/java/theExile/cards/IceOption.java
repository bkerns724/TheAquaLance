package theExile.cards;

import static theExile.ExileMod.makeID;

public class IceOption extends AbstractExileCard {
    public final static String ID = makeID(IceOption.class.getSimpleName());
    private final static int COST = -2;

    public IceOption() {
        super(ID, COST, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
    }

    public void applyAttributes() {
    }

    @Override
    public void onChoseThisOption() {
    }

    public void upp() {
    }
}