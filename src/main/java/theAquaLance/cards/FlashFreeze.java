package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class FlashFreeze extends AbstractEasyCard {
    public final static String ID = makeID("FlashFreeze");
    private final static int BLOCK = 8;
    private final static int UPGRADE_BLOCK = 3;
    private final static int COST = 1;

    public FlashFreeze() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseBlock = BLOCK;
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}