package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.ICE;
import static theExile.util.Wiz.cDraw;

public class FrozenLance extends AbstractExileCard {
    public final static String ID = makeID(FrozenLance.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public FrozenLance() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        addModifier(ICE);
    }

    @Override
    public void nonTargetUse() {
        cDraw(magicNumber);
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}