package theExile.cards;

import theExile.actions.WeaveAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class Weave extends AbstractExileCard {
    public final static String ID = makeID(Weave.class.getSimpleName());
    private final static int COST = 0;

    public Weave() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
    }

    public void nonTargetUse() {
        atb(new WeaveAction());
    }

    public void upp() {
        selfRetain = true;
    }
}