package theExile.cards;

import theExile.powers.DiscardNextTurnPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Blink extends AbstractExileCard {
    public final static String ID = makeID(Blink.class.getSimpleName());
    private final static int BLOCK = 11;
    private final static int UPGRADE_BLOCK = 3;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public Blink() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseBlock = BLOCK;
    }

    public void nonTargetUse() {
        blck();
        applyToSelf(new DiscardNextTurnPower(magicNumber));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}