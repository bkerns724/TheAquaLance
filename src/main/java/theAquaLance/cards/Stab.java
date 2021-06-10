package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.HobbledPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Stab extends AbstractEasyCard {
    public final static String ID = makeID("Stab");
    private final static int DAMAGE = 7;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 1;
    private final static int MAGIC_UPG = 1;

    public Stab() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        applyToEnemy(m, new HobbledPower(m, magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(MAGIC_UPG);
    }
}