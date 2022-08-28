package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getBluntEffect;
import static theExile.util.Wiz.getJinxAmount;

public class WeightedStaff extends AbstractExileCard {
    public final static String ID = makeID(WeightedStaff.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public WeightedStaff() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m, getBluntEffect(damage));
        int amount = getJinxAmount(m);
        if (amount > 0) {
            baseBlock = amount;
            applyPowers();
            blck();
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}