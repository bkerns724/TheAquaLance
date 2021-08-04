package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theAquaLance.powers.HobbledPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SteamWall extends AbstractEasyCard {
    public final static String ID = makeID("SteamWall");
    private final static int BLOCK = 11;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public SteamWall() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        applyToEnemy(m, new HobbledPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}