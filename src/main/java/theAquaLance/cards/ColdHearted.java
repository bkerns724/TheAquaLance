package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.ColdHeartedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class ColdHearted extends AbstractEasyCard {
    public final static String ID = makeID("ColdHearted");
    private final static int MAGIC = 3;

    public ColdHearted() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ColdHeartedPower(p, 1));
    }

    public void upp() {
        isInnate = true;
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}