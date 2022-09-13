package theExile.cards;

import theExile.ExileMod;
import theExile.powers.ElementalProwessEldritch;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class EldritchOption extends AbstractExileCard {
    public final static String ID = makeID(EldritchOption.class.getSimpleName());
    private final static int COST = -2;

    public EldritchOption() {
        super(ID, COST, CardType.POWER, ExileMod.Enums.CARD_CHOICE, CardTarget.SELF);
    }

    public void applyAttributes() {
    }

    @Override
    public void onChoseThisOption() {
        applyToSelf(new ElementalProwessEldritch(1));
    }

    public void upp() {
    }
}