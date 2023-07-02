package theExile.cards;

import theExile.actions.SiphonAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class Siphon extends AbstractExileCard {
    public final static String ID = makeID(Siphon.class.getSimpleName());
    private final static int BLOCK = 4;
    private final static int UPGRADE_BLOCK = 2;
    private final static int COST = 0;

    public Siphon() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseBlock = BLOCK;
    }

    @Override
    public void nonTargetUse() {
        atb(new SiphonAction(block));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}