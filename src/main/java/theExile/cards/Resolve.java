package theExile.cards;

import theExile.powers.ResolvePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Resolve extends AbstractExileCard {
    public final static String ID = makeID(Resolve.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public Resolve() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new ResolvePower(magicNumber));
    }

    public void upp() {
        isInnate = true;
    }
}