package theExile.cards;

import static theExile.ExileMod.makeID;

public class Reverberation extends AbstractResonantCard {
    public final static String ID = makeID(Reverberation.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public Reverberation() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    protected void setResonance() {
        resonance.vigor = MAGIC;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        resonance.vigor += UPGRADE_MAGIC;
    }
}