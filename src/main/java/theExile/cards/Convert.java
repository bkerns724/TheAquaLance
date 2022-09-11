package theExile.cards;

import theExile.powers.ConvertPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Convert extends AbstractExileCard {
    public final static String ID = makeID(Convert.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 2;

    public Convert() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void nonTargetUse() {
        applyToSelf(new ConvertPower(magicNumber));
    }

    public void upp() {
        exhaust = false;
    }
}