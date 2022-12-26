package theExile.cards;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.LIGHTNING;

public class BallLightning extends AbstractExileCard {
    public final static String ID = makeID(BallLightning.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public BallLightning() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(LIGHTNING);
    }

    @Override
    public void nonTargetUse() {
        allDmg();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}