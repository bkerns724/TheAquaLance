package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.cards.AbstractEasyCard;
import theAquaLance.powers.IceMasteryPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class IceMastery extends AbstractEasyCard {
    public final static String ID = makeID("IceMastery");
    private final static int UPGRADED_COST = 1;

    public IceMastery() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IceMasteryPower(adp(), 1));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}