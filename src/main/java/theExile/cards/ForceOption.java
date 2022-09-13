package theExile.cards;

import theExile.powers.ElementalProwessForce;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class ForceOption extends AbstractExileCard {
    public final static String ID = makeID(ForceOption.class.getSimpleName());
    private final static int COST = -2;

    public ForceOption() {
        super(ID, COST, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void applyAttributes() {
    }

    @Override
    public void onChoseThisOption() {
        applyToSelf(new ElementalProwessForce(1));
    }

    public void upp() {
    }
}