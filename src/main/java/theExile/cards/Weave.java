package theExile.cards;

import theExile.actions.WeaveAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class Weave extends AbstractExileCard {
    public final static String ID = makeID(Weave.class.getSimpleName());
    private final static int COST = 1;
    private final static int BLOCK = 4;
    private final static int UPGRADE_BLOCK = 3;

    public Weave() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseBlock = BLOCK;
    }

    public void nonTargetUse() {
        blck();
        atb(new WeaveAction(magicNumber));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}