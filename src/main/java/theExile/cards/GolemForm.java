package theExile.cards;

import theExile.powers.GolemPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class GolemForm extends AbstractExileCard {
    public final static String ID = makeID(GolemForm.class.getSimpleName());
    private final static int MAGIC = 50;
    private final static int COST = 3;

    public GolemForm() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        isEthereal = true;
    }

    public void nonTargetUse() {
        applyToSelf(new GolemPower(magicNumber));
    }

    public void upp() {
        isEthereal = false;
    }
}