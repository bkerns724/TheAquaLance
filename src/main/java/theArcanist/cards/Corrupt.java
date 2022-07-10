package theArcanist.cards;

import static theArcanist.ArcanistMod.makeID;

public class Corrupt extends AbstractResonantCard {
    public final static String ID = makeID(Corrupt.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Corrupt() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void setResonance() {
        resonance.decay = baseMagicNumber;
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        resonance.decay += UPGRADE_MAGIC;
    }
}