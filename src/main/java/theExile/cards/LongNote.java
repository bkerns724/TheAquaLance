package theExile.cards;

import static theExile.ExileMod.makeID;

public class LongNote extends AbstractResonantCard {
    public final static String ID = makeID(LongNote.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public LongNote() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    protected void setResonance() {
        resonance.ringing = baseMagicNumber;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        resonance.ringing += UPGRADE_MAGIC;
    }
}