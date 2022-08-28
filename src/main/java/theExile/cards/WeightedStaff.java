package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

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

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, getBluntEffect());
        int amount = getJinxAmountCard(m);
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