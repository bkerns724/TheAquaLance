package theExile.cards;

import static theExile.ExileMod.makeID;

public class Beat extends AbstractResonantCard {
    public final static String ID = makeID(Beat.class.getSimpleName());
    private final static int DAMAGE = 2;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public Beat() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    protected void setResonance() {
        resonance.damage = baseDamage;
        resonance.cycle = magicNumber;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        resonance.damage += UPGRADE_DAMAGE;
    }
}