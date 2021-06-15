package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.cards.AbstractEasyCard;
import theAquaLance.powers.RapidsPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Rapids extends AbstractEasyCard {
    public final static String ID = makeID("Rapids");
    private final static int UPGRADED_COST = 0;

    public Rapids() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RapidsPower(adp(), 1));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}