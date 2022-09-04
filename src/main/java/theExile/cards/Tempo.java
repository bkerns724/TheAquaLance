package theExile.cards;

import static theExile.ExileMod.makeID;

public class Tempo extends AbstractResonantCard {
    public final static String ID = makeID(Tempo.class.getSimpleName());
    private final static int DAMAGE = 4;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 50;
    private final static int COST = 1;

    // ATTACK, UNCOMMON, ENEMY
    public Tempo() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    protected void setResonance() {
        resonance.damage = baseDamage;
        resonance.charge = magicNumber;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        resonance.damage += UPGRADE_DAMAGE;
    }
}