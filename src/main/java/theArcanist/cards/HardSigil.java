package theArcanist.cards;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class HardSigil extends AbstractSigilCard {
    public final static String ID = makeID("HardSigil");
    private final static int BLOCK = 10;
    private final static int UPGRADE_BLOCK = 3;

    public HardSigil() {
        super(ID, CardRarity.COMMON, CardType.SKILL, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    @Override
    public void onManualDiscard() {
        blck();
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}