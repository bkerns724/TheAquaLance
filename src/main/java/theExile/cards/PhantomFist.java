package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.cardDraw;

public class PhantomFist extends AbstractExileCard {
    public final static String ID = makeID(PhantomFist.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 2;
    private final static int COST = 1;

    public PhantomFist() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        addModifier(elenum.FORCE);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    @Override
    public void nonTargetUse() {
        cardDraw(magicNumber);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}