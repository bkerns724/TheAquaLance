package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class LeadBall extends AbstractExileCard {
    public final static String ID = makeID(LeadBall.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public LeadBall() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        if (damageModList.isEmpty())
            dmg(m, Wiz.getBluntEffect(damage));
        else
            dmg(m);
    }

    @Override
    public void nonTargetUse() {
        Wiz.discard(magicNumber);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}