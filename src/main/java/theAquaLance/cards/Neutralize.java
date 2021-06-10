package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.powers.HobbledPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Neutralize extends AbstractEasyCard {
    public final static String ID = makeID("Neutralize");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public Neutralize() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AquaLanceMod.Enums.WATER);
        applyToEnemy(m, new HobbledPower(m, magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}