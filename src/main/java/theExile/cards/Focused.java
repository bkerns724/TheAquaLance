package theExile.cards;

import theExile.powers.FocusedPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Focused extends AbstractExileCard {
    public final static String ID = makeID(Focused.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public Focused() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new FocusedPower(magicNumber));
    }

    public void upp() {
        isInnate = true;
    }
}