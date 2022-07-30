package theExile.cards;

import static theExile.ExileMod.makeID;

public class Disorient extends AbstractResonantCard {
    public final static String ID = makeID(Disorient.class.getSimpleName());
    private final static int BLOCK = 10;
    private final static int UPGRADE_BLOCK = 3;
    private final static int COST = 1;

    public Disorient() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    protected void setResonance() {
        resonance.block = baseBlock;
    }

    @Override
    protected void applyAttributes() {
        baseBlock = BLOCK;
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        resonance.block += UPGRADE_BLOCK;
    }
}