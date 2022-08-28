package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class EldritchTentacle extends AbstractExileCard {
    public final static String ID = makeID(EldritchTentacle.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public EldritchTentacle() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.ELDRITCH);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
        applyToEnemy(m, new JinxPower(m, magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}