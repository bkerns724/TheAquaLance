package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.SoakedPower;
import theAquaLance.powers.VitalityPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class EvasiveManeuvers extends AbstractEasyCard {
    public final static String ID = makeID("EvasiveManeuvers");
    private final static int BLOCK = 8;
    private final static int UPGRADE_BLOCK = 3;
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;

    public EvasiveManeuvers() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new VitalityPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}