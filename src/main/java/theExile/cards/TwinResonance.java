package theExile.cards;

import static theExile.ExileMod.makeID;

public class TwinResonance extends AbstractResonantCard {
    public final static String ID = makeID(TwinResonance.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 2;
    private final static int COST = 1;

    public TwinResonance() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    protected void setResonance() {
        resonance.damage = baseDamage*magicNumber;
        resonance.multiHit = baseMagicNumber;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        resonance.damage += UPGRADE_DAMAGE*baseMagicNumber;
    }
}