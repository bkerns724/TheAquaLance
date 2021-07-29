package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.IceMasteryPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class IceMastery extends AbstractEasyCard {
    public final static String ID = makeID("IceMastery");
    private final static int MAGIC = 7;
    private final static int UPGRADE_MAGIC = 3;
    private final static int COST = 2;

    public IceMastery() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = MAGIC;
        isInnate = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IceMasteryPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}