package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.FinisherAction;
import theAquaLance.powers.DrowningPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Engulf extends AbstractEasyCard {
    public final static String ID = makeID("Engulf");
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 6;
    private final static int MAGIC = 1;

    public Engulf() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int shardCount = getShardCount(m);
        applyToEnemy(m, new DrowningPower(m, magicNumber*shardCount));
        atb(new FinisherAction(m));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}