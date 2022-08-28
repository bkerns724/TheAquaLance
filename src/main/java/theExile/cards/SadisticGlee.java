package theExile.cards;

import theExile.powers.SadisticGleePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class SadisticGlee extends AbstractExileCard {
    public final static String ID = makeID(SadisticGlee.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 2;

    public SadisticGlee() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new SadisticGleePower(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}