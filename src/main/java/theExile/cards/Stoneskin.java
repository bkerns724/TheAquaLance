package theExile.cards;

import theExile.powers.StoneskinPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class Stoneskin extends AbstractExileCard {
    public final static String ID = makeID(Stoneskin.class.getSimpleName());
    private final static int BLOCK = 4;
    private final static int UPGRADE_BLOCK = 2;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Stoneskin() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        blck();
        applyToSelf(new StoneskinPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}