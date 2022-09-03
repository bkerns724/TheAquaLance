package theExile.cards;

import theExile.actions.SackOfSandAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class SackOfSand extends AbstractExileCard {
    public final static String ID = makeID(SackOfSand.class.getSimpleName());
    private final static int BLOCK = 5;
    private final static int UPGRADE_BLOCK = 2;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public SackOfSand() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        selfRetain = true;
    }

    public void nonTargetUse() {
        atb(new SackOfSandAction(this));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upMagic(UPGRADE_MAGIC);
    }
}