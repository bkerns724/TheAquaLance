package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theAquaLance.powers.IntelligencePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Reckless extends AbstractEasyCard {
    public final static String ID = makeID("Reckless");
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int SECOND_MAGIC = 2;
    private final static int UPGRADE_SECOND = 1;

    public Reckless() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(adp(), magicNumber));
        applyToSelf(new IntelligencePower(adp(), magicNumber));
        applyToSelf(new DexterityPower(adp(), -secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeSecondMagic(UPGRADE_SECOND);
    }
}