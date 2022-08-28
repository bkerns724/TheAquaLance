package theExile.cards;

import static theExile.ExileMod.makeID;

public class HardSigil extends AbstractExileCard {
    public final static String ID = makeID(HardSigil.class.getSimpleName());
    private final static int BLOCK = 9;
    private final static int UPGRADE_BLOCK = 3;

    public HardSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseBlock = BLOCK;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        blck();
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}