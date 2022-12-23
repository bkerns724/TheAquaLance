package theExile.cards;

import static theExile.ExileMod.makeID;

public class BadVibes extends AbstractResonantCard {
    public final static String ID = makeID(BadVibes.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 1;
    private final static int COST = 1;

    public BadVibes() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void setResonance() {
        resonance.jinx = baseMagicNumber;
        resonance.discard = baseSecondMagic;
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        resonance.jinx += UPGRADE_MAGIC;
    }
}