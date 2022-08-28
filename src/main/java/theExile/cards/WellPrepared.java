package theExile.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;
import static theExile.util.Wiz.discard;

public class WellPrepared extends AbstractExileCard {
    public final static String ID = makeID(WellPrepared.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 1;
    private final static int COST = 0;

    public WellPrepared() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        secondMagic = baseSecondMagic = SECOND_MAGIC;
        exhaust = true;
    }

    public void nonTargetUse() {
        atb(new DrawCardAction(magicNumber));
        discard(secondMagic);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}