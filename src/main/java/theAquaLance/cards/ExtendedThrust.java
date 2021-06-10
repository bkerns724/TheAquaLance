package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.DiscardNextTurnPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class ExtendedThrust extends AbstractEasyCard {
    public final static String ID = makeID("ExtendedThrust");
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 1;

    public ExtendedThrust() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        atb(new DiscardAction(p, p, magicNumber, false));
        applyToSelf(new DiscardNextTurnPower(p, magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}