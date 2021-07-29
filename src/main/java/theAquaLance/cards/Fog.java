package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.TempNegStrengthPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Fog extends AbstractEasyCard {
    public final static String ID = makeID("Fog");
    private final static int BLOCK = 5;
    private final static int UPGRADE_BLOCK = 2;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public Fog() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToEnemy(m, new TempNegStrengthPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeBlock(UPGRADE_BLOCK);
    }
}