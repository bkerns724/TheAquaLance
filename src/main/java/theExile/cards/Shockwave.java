package theExile.cards;

import static theExile.ExileMod.makeID;

public class Shockwave extends AbstractResonantCard {
    public final static String ID = makeID(Shockwave.class.getSimpleName());
    private final static int BLOCK = 9;
    private final static int UPGRADE_BLOCK = 3;
    private final static int COST = 1;

    public Shockwave() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
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