package theExile.cards;

import static theExile.ExileMod.makeID;

public class Amplify extends AbstractResonantCard {
    public final static String ID = makeID(Amplify.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Amplify() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void setResonance() {
        resonance.amplify = baseMagicNumber;
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        resonance.amount += UPGRADE_DAMAGE;
        upMagic(UPGRADE_MAGIC);
        resonance.amplify += UPGRADE_MAGIC;
    }
}