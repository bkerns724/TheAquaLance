package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.getDebuffCount;

public class ShadowWhip extends AbstractExileCard {
    public final static String ID = makeID(ShadowWhip.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public ShadowWhip() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.ELDRITCH);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
        applyToEnemy(m, new JinxPower(m, getDebuffCount(m)));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}