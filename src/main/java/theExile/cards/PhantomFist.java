package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class PhantomFist extends AbstractExileCard {
    public final static String ID = makeID(PhantomFist.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 6;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public PhantomFist() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
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
        if (mo.getIntentBaseDmg() > 0)
            baseDamage += magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = baseDamage != damage;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}