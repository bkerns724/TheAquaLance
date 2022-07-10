package theExile.cards;

import static theExile.ExileMod.makeID;

public class Deter extends AbstractResonantCard {
    public final static String ID = makeID(Deter.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Deter() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void setResonance() {
        resonance.revenge = baseMagicNumber;
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        resonance.revenge += UPGRADE_MAGIC;
    }
}