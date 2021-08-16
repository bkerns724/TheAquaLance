package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Flurry extends AbstractEasyCard {
    public final static String ID = makeID("Flurry");
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;

    public Flurry() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < getDebuffCount(m); i++)
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}