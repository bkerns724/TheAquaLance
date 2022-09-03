package theExile.cards;

import theExile.ExileMod;
import theExile.powers.SummonMonsterPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class SustainedSummon extends AbstractExileCard {
    public final static String ID = makeID(SustainedSummon.class.getSimpleName());
    private final static int MAGIC = 12;
    private final static int UPGRADE_MAGIC = 4;
    private final static int COST = 2;

    public SustainedSummon() {
        super(ID, COST, CardType.POWER, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void nonTargetUse() {
        applyToSelf(new SummonMonsterPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}