package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.AquaDrawCardAction;
import theAquaLance.powers.DrowningPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Surf extends AbstractEasyCard {
    public final static String ID = makeID("Surf");
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int MAGIC_TWO = 3;

    public Surf() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = MAGIC_TWO;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AquaDrawCardAction(magicNumber));
        applyToEnemy(m, new DrowningPower(m, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}