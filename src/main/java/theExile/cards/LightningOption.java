package theExile.cards;

import theExile.powers.ElementalProwessLightning;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class LightningOption extends AbstractExileCard {
    public final static String ID = makeID(LightningOption.class.getSimpleName());
    private final static int COST = -2;

    public LightningOption() {
        super(ID, COST, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void applyAttributes() {
    }

    @Override
    public void onChoseThisOption() {
        applyToSelf(new ElementalProwessLightning(1));
    }

    public void upp() {
    }
}