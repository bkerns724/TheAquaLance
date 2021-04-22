package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.IceStormPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class IceStorm extends AbstractEasyCard {
    public final static String ID = makeID("IceStorm");
    private final static int MAGIC = 1;
    private final static int UPGRADED_COST = 0;
    private final static int SECOND_MAGIC = 2;

    public IceStorm() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IceStormPower(p, magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}