package theExile.cards;

import theExile.ExileMod;
import theExile.powers.SummonMonsterPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class SummonMonster extends AbstractExileCard {
    public final static String ID = makeID(SummonMonster.class.getSimpleName());
    private final static int MAGIC = 15;
    private final static int UPGRADE_MAGIC = 5;
    private final static int COST = 2;

    public SummonMonster() {
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