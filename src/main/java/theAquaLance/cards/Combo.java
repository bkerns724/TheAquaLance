package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.PlayDiscardShardAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Combo extends AbstractEasyCard {
    public final static String ID = makeID("Combo");
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 1;

    public Combo() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        atb(new PlayDiscardShardAction(m));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}