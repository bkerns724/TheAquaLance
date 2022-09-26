package theExile.cards;

import theExile.powers.DrawNextTurnPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Focus extends AbstractExileCard {
    public final static String ID = makeID(Focus.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 1;
    private final static int COST = 1;

    public Focus() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void nonTargetUse() {
        Wiz.discard(secondMagic);
        applyToSelf(new DrawNextTurnPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}