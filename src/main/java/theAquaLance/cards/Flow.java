package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Flow extends AbstractEasyCard {
    public final static String ID = makeID("Flow");
    private final static int UPGRADED_COST = 0;
    private final static int COST = 1;

    public Flow() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!adp().hand.isEmpty())
            atb(new GamblingChipAction(adp(), true));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}