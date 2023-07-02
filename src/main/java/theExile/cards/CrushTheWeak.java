package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theExile.ExileMod.makeID;

public class CrushTheWeak extends AbstractExileCard {
    public final static String ID = makeID(CrushTheWeak.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 2;
    private final static int UGPRADE_MAGIC = 1;
    private final static int COST = 1;

    public CrushTheWeak() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
    public void calculateCardDamage(AbstractMonster m) {
        AbstractPower pow = m.getPower(WeakPower.POWER_ID);
        if (pow != null) {
            int temp = baseDamage;
            baseDamage += pow.amount * magicNumber;
            super.calculateCardDamage(m);
            baseDamage = temp;
            isDamageModified = baseDamage != damage;
        }
        else
            super.calculateCardDamage(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UGPRADE_MAGIC);
    }
}