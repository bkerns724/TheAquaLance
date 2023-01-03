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
    private final static int COST = 1;

    public FrozenLance() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(ICE);
    }

    @Override
    public AbstractMonster getTarget() {
        AbstractMonster left = null;
        for (AbstractMonster m : Wiz.getEnemies()) {
            if (left == null)
                left = m;
            else if (m.hb.cX < left.hb.cX)
                left = m;
        }
        return left;
    }

    @Override
    public void autoTargetUse(AbstractMonster m) {
        dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}