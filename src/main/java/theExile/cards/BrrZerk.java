package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.brrZerkAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class BrrZerk extends AbstractExileCard {
    public final static String ID = makeID(BrrZerk.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = -1;

    public BrrZerk() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.ICE);
        exhaust = true;
    }

    public void singleTargetUse(AbstractMonster m) {
        atb(new brrZerkAction(m, this, freeToPlayOnce, energyOnUse));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}