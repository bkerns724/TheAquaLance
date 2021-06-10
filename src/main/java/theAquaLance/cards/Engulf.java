package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.PopAction;
import theAquaLance.powers.SoakedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Engulf extends AbstractEasyCard {
    public final static String ID = makeID("Engulf");
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int MAGIC = 6;
    private final static int UPGRADE_MAGIC = 2;
    private final static int POP_AMOUNT = 3;

    public Engulf() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AquaLanceMod.Enums.WATER);
        int count = getShardCount(m);
        if (count >= POP_AMOUNT) {
            applyToEnemy(m, new SoakedPower(m, magicNumber));
            atb(new PopAction(m, POP_AMOUNT));
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}