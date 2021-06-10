package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class PerfectedThrust extends AbstractEasyCard {
    public final static String ID = makeID("PerfectedThrust");
    private final static int DAMAGE = 11;
    private final static int UPGRADE_DAMAGE = 4;

    public PerfectedThrust() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}