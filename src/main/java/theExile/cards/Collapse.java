package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class Collapse extends AbstractExileCard {
    public final static String ID = makeID(Collapse.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 2;

    public Collapse() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.PHANTASMAL);
    }

    @Override
    public void nonTargetUse() {
        allDmg();
    }

    @Override
    public void applyPowers() {
        int temp = baseDamage;
        baseDamage += magicNumber * Wiz.getEnemies().size();
        super.applyPowers();
        baseDamage = temp;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        baseDamage += magicNumber * Wiz.getEnemies().size();
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = baseDamage != damage;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}