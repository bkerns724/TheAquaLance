package theExile.cards;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.cDraw;

public class Barrier extends AbstractExileCard {
    public final static String ID = makeID(Barrier.class.getSimpleName());
    private final static int BLOCK = 8;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Barrier() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        blck();
        cDraw(magicNumber);
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}