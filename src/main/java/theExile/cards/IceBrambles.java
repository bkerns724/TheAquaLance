package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class IceBrambles extends AbstractExileCard {
    public final static String ID = makeID(IceBrambles.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = DAMAGE;
    private final static int COST = 1;

    public IceBrambles() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        addModifier(elenum.ICE);
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
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}