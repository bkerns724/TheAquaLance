package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class ForceBolt extends AbstractExileCard {
    public final static String ID = makeID(ForceBolt.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public ForceBolt() {
        super(ID, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.PHANTASMAL);
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
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