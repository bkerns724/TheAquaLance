package theExile.cards;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.discard;

public class Flare extends AbstractExileCard {
    public final static String ID = makeID(Flare.class.getSimpleName());
    private final static int BLOCK = 6;
    private final static int UPGRADE_BLOCK = 2;
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public Flare() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        blck();
        discard(1);
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}