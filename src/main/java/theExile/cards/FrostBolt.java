package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class FrostBolt extends AbstractExileCard {
    public final static String ID = makeID(FrostBolt.class.getSimpleName());
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public FrostBolt() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.ICE);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        baseDamage += magicNumber * Wiz.getDebuffCount(mo);
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = baseDamage != damage;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}