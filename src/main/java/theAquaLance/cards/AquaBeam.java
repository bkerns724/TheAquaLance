package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.powers.SoakedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class AquaBeam extends AbstractEasyCard {
    public final static String ID = makeID("AquaBeam");
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;

    public AquaBeam() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new SoakedPower(m, magicNumber), AquaLanceMod.Enums.WATER);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}