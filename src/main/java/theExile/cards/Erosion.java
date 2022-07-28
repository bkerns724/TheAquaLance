package theExile.cards;

import static theExile.ExileMod.makeID;

public class Erosion extends AbstractResonantCard {
    public final static String ID = makeID(Erosion.class.getSimpleName());
    private final static int DAMAGE = 4;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 8;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public Erosion() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    protected void setResonance() {
        resonance.damage = baseDamage;
        resonance.multiHit = baseMagicNumber;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        resonance.damage += UPGRADE_DAMAGE;
        upgradeMagicNumber(UPGRADE_MAGIC);
        resonance.erosion += UPGRADE_MAGIC;
    }
}