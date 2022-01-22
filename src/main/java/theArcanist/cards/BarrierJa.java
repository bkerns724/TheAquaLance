package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class BarrierJa extends AbstractEasyCard {
    public final static String ID = makeID("BarrierJa");
    private final static int BLOCK = 15;
    private final static int UPGRADE_BLOCK = 5;
    private final static int COST = 2;

    // skill, uncommon, self
    public BarrierJa() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}