package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.FrostbitePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Freeze extends AbstractEasyCard {
    public final static String ID = makeID("Freeze");
    private final static int MAGIC = 12;
    private final static int UPGRADE_MAGIC = 4;
    private final static int COST = 2;

    public Freeze() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new FrostbitePower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}