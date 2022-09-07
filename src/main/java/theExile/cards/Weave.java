package theExile.cards;

import theExile.actions.WeaveAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class Weave extends AbstractExileCard {
    public final static String ID = makeID(Weave.class.getSimpleName());
    private final static int COST = 0;
    private final static int MAGIC = 2;

    public Weave() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        if (!upgraded)
            atb(new WeaveAction(magicNumber));
        else
            atb(new WeaveAction(99));
    }

    public void upp() {
    }
}