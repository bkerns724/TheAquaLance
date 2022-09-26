package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.ICE;

public class FrozenLance extends AbstractExileCard {
    public final static String ID = makeID(FrozenLance.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public FrozenLance() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        addModifier(ICE);
    }

    @Override
    public void autoTargetUse(AbstractMonster m) {
        calculateCardDamage(m);
        dmg(m);
    }

    @Override
    public AbstractMonster getTarget() {
        float left = 5000000;
        AbstractMonster target = null;
        for (AbstractMonster m : Wiz.getEnemies()) {
            if (m.hb.x < left) {
                target = m;
                left = m.hb.x;
            }
        }
        return target;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}