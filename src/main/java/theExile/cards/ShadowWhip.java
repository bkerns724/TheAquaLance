package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.JinxOnHitPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class ShadowWhip extends AbstractExileCard {
    public final static String ID = makeID(ShadowWhip.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public ShadowWhip() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.ELDRITCH);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
        Wiz.applyToEnemy(m, new JinxOnHitPower(m, magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}