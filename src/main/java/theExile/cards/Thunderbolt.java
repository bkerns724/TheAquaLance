package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getHighestHealthEnemy;

public class Thunderbolt extends AbstractExileCard {
    public final static String ID = makeID(Thunderbolt.class.getSimpleName());
    private final static int DAMAGE = 11;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public Thunderbolt() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.LIGHTNING);
    }

    @Override
    public AbstractMonster getTarget() {
        return getHighestHealthEnemy();
    }

    @Override
    public void autoTargetUse(AbstractMonster m) {
        calculateCardDamage(m);
        dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}