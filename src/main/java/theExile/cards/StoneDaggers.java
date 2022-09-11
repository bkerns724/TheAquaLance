package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class StoneDaggers extends AbstractExileCard {
    public final static String ID = makeID(StoneDaggers.class.getSimpleName());
    private final static int DAMAGE = 4;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = 1;

    public StoneDaggers() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        int count = Wiz.getDebuffCount(m);
        for (int i = 0; i < count; i++) {
            if (damageModList.isEmpty())
                dmg(m, Wiz.getSlashEffect(damage));
            else
                dmg(m);
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}