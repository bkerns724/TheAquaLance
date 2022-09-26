package theExile.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class SpinToWin extends AbstractExileCard {
    public final static String ID = makeID(SpinToWin.class.getSimpleName());
    private final static int BLOCK = 7;
    private final static int UPGRADE_BLOCK = 3;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public SpinToWin() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        blck();
        Wiz.draw(magicNumber);
        Wiz.discard(magicNumber);
        atb(new MakeTempCardInDiscardAction(makeStatEquivalentCopy(), magicNumber));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}