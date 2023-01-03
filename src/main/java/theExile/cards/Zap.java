package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.cDraw;
import static theExile.util.Wiz.getSlashEffect;

public class Zap extends AbstractExileCard {
    public final static String ID = makeID(Zap.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 2;
    private final static int COST = 1;

    public Zap() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.LIGHTNING);
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        if (damageModList.isEmpty())
            dmg(m, getSlashEffect(damage));
        else
            dmg(m);
        cDraw(magicNumber);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}