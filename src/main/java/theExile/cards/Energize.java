package theExile.cards;

import static theExile.ExileMod.makeID;

public class Energize extends AbstractResonantCard {
    public final static String ID = makeID(Energize.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 2;
    private final static int SECOND_MAGIC = 1;
    private final static int COST = 1;

    public Energize() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    @Override
    protected void setResonance() {
        resonance.vigor = magicNumber;
        resonance.draw = secondMagic;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        resonance.vigor += UPGRADE_MAGIC;
    }
}