package theExile.cards;

import static theExile.ExileMod.makeID;

public class Amplify extends AbstractResonantCard {
    public final static String ID = makeID(Amplify.class.getSimpleName());
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public Amplify() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void setResonance() {
        resonance.damage = baseDamage;
        resonance.amplify = baseMagicNumber;
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        resonance.damage += UPGRADE_DAMAGE;
        upMagic(UPGRADE_MAGIC);
        resonance.amplify += UPGRADE_MAGIC;
    }
}