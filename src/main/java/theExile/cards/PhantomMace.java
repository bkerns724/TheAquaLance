package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class PhantomMace extends AbstractExileCard {
    public final static String ID = makeID(PhantomMace.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 6;
    private final static int UPGRADE_MAGIC = 3;
    private final static int COST = 2;

    public PhantomMace() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FORCE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        if (mo != null && mo.getIntentBaseDmg() >= 0)
            baseDamage += magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = baseDamage != damage;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        upgradeDamage(UPGRADE_DAMAGE);
    }
}