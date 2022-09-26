package theExile.cards;

import static theExile.ExileMod.makeID;

public class LightningOption extends AbstractExileCard {
    public final static String ID = makeID(LightningOption.class.getSimpleName());
    private final static int COST = -2;

    public LightningOption() {
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