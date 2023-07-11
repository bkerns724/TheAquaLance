package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.FlingAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class Fling extends AbstractExileCard {
    public final static String ID = makeID(Fling.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int SECOND_MAGIC = 10;
    private final static int UPGRADE_SECOND = 4;
    private final static int COST = 1;

    public Fling() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        atb(new FlingAction(m, magicNumber, secondMagic));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        upMagic2(UPGRADE_SECOND);
    }
}