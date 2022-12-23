package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class EldritchBolt extends AbstractExileCard {
    public final static String ID = makeID(EldritchBolt.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 2;
    private final static int COST = 1;

    public EldritchBolt() {
        super(ID, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.ELDRITCH);
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
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