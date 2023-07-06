package theExile.cards;

import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class Research extends AbstractExileCard {
    public final static String ID = makeID(Research.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Research() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        Wiz.cDraw(magicNumber);
        atb(new PutOnDeckAction(adp(), adp(), 1, false));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}