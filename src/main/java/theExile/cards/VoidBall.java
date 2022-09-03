package theExile.cards;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.ELDRITCH;
import static theExile.util.Wiz.discard;

public class VoidBall extends AbstractExileCard {
    public final static String ID = makeID(VoidBall.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public VoidBall() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        isMultiDamage = true;
        addModifier(ELDRITCH);
    }

    public void nonTargetUse() {
        allDmg();
        discard(magicNumber);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}