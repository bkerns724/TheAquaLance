package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.PermafrostPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class Permafrost extends AbstractExileCard {
    public final static String ID = makeID(Permafrost.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public Permafrost() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.ICE);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
        Wiz.applyToEnemy(m, new PermafrostPower(m, 1));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}