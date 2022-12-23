package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;

public class Thunder extends AbstractExileCard {
    public final static String ID = makeID(Thunder.class.getSimpleName());
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int COST = 2;

    public Thunder() {
        super(ID, COST, CardType.ATTACK, ExileMod.Enums.UNIQUE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.LIGHTNING);
        addModifier(elenum.FORCE);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}