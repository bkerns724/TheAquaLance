package theExile.cards;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.LIGHTNING;

public class BallLightning extends AbstractExileCard {
    public final static String ID = makeID(BallLightning.class.getSimpleName());
    private final static int DAMAGE = 2;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public BallLightning() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        addModifier(LIGHTNING);
    }

    @Override
    public void nonTargetUse() {
        for (int i = 0; i < magicNumber; i++)
            allDmg();
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}