package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.IntelligencePower;
import theAquaLance.powers.LoseIntPower;
import theAquaLance.powers.SoakedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Surge extends AbstractEasyCard {
    public final static String ID = makeID("Surge");
    private final static int MAGIC = 1;
    private final static int COST = 1;
    private final static int SECOND_MAGIC = 4;
    private final static int UPGRADE_SECOND = 2;

    public Surge() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(mo -> applyToEnemy(mo, new SoakedPower(mo, magicNumber)));
        applyToSelf(new IntelligencePower(adp(), secondMagic));
        applyToSelf(new LoseIntPower(adp(), secondMagic));
    }

    public void upp() {
        upgradeSecondMagic(UPGRADE_SECOND);
    }
}