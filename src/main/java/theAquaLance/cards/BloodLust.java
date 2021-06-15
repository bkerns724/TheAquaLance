package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.BloodLustPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BloodLust extends AbstractEasyCard {
    public final static String ID = makeID("BloodLust");
    private final static int UPGRADED_COST = 1;

    public BloodLust() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BloodLustPower(adp(), 1));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}