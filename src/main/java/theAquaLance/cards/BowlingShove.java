package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theAquaLance.cards.AbstractEasyCard;

import java.util.ArrayList;
import java.util.function.Consumer;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BowlingShove extends AbstractEasyCard {
    public final static String ID = makeID("BowlingShove");
    private final static int DAMAGE = 12;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public BowlingShove() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        ArrayList<AbstractMonster> enemies = getEnemies();
        for (AbstractMonster mo : enemies) {
            if (m == mo)
                applyToEnemy(mo, new VulnerablePower(mo, magicNumber, false));
            else
                applyToEnemy(mo, new WeakPower(mo, magicNumber, false));
        }
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}