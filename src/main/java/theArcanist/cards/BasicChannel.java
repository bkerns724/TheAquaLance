package theArcanist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.patches.ResonantPowerPatch;
import theArcanist.powers.ResonatingPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class BasicChannel extends AbstractArcanistCard {
    public final static String ID = makeID("BasicChannel");
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public BasicChannel() {
        super(ID, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        ResonantPowerPatch.AbstractCardField.resonance.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        applyToSelf(new ResonatingPower(p, baseDamage, false, false, false, false, 0, 0));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}