package theExile.cards;

import theExile.powers.ElementalProwessIce;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class IceOption extends AbstractExileCard {
    public final static String ID = makeID(IceOption.class.getSimpleName());
    private final static int COST = -2;

    public IceOption() {
        super(ID, COST, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void applyAttributes() {
    }

    @Override
    public void onChoseThisOption() {
        applyToSelf(new ElementalProwessIce(1));
    }

    public void upp() {
    }
}