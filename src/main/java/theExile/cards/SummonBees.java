package theExile.cards;

import theExile.powers.BeesPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class SummonBees extends AbstractExileCard {
    public final static String ID = makeID(SummonBees.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 3;
    private final static int COST = 1;

    public SummonBees() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        forAllMonstersLiving(m -> applyToEnemy(m, new BeesPower(m, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}