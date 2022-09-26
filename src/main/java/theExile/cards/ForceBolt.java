package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class ForceBolt extends AbstractExileCard {
    public final static String ID = makeID(ForceBolt.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 2;
    private final static int COST = 1;

    public ForceBolt() {
        super(ID, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FORCE);
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        if (damageModList.isEmpty())
            dmg(m, Wiz.getSlashEffect(damage));
        else
            dmg(m);
    }

    @Override
    public void nonTargetUse() {
        Wiz.discard(1);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}