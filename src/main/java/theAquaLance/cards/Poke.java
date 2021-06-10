package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.PopAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Poke extends AbstractEasyCard {
    public final static String ID = makeID("Poke");
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int POP_AMOUNT = 1;

    public Poke() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        if (getShardCount(m) >= POP_AMOUNT) {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
            atb(new PopAction(m, POP_AMOUNT));
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}