package theExile.cards;

import static theExile.ExileMod.makeID;

public class SplitResonance extends AbstractResonantCard {
    public final static String ID = makeID(SplitResonance.class.getSimpleName());
    private final static int DAMAGE = 2;
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public SplitResonance() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    protected void setResonance() {
        resonance.damage = baseDamage*baseMagicNumber;
        resonance.multi = magicNumber;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        resonance.multi += UPGRADE_MAGIC;
        resonance.damage = baseDamage * baseMagicNumber;
    }
}