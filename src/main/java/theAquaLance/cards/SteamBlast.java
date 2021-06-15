package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SteamBlast extends AbstractEasyCard {
    public final static String ID = makeID("SteamBlast");
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_DAMAGE = 4;

    public SteamBlast() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmgTwo(m, AbstractGameAction.AttackEffect.FIRE);
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}