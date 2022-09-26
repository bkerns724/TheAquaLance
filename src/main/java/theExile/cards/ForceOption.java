package theExile.cards;

import static theExile.ExileMod.makeID;

public class ForceOption extends AbstractExileCard {
    public final static String ID = makeID(ForceOption.class.getSimpleName());
    private final static int COST = -2;

    public ForceOption() {
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