package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class Collapse extends AbstractExileCard {
    public final static String ID = makeID(Collapse.class.getSimpleName());
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 10;
    private final static int UPGRADE_MAGIC = -2;
    private final static int COST = 2;

    public Collapse() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FORCE);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        baseDamage += (mo.maxHealth - mo.currentHealth)/magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = damage != baseDamage;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        upgradeDamage(UPGRADE_DAMAGE);
    }
}