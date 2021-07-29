package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theAquaLance.powers.HobbledPower;
import theAquaLance.powers.OverExtendPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SteamExplosion extends AbstractEasyCard {
    public final static String ID = makeID("SteamExplosion");
    private final static int DAMAGE = 6;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 5;

    public SteamExplosion() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        applyToEnemy(m, new HobbledPower(m, magicNumber));
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        applyToSelf(new OverExtendPower(adp(), secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}