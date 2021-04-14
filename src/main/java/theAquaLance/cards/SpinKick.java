package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.actions.AquaDrawCardAction;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SpinKick extends AbstractEasyCard {
    public final static String ID = makeID("SpinKick");
    private final static int DAMAGE = 7;
    private final static int UPGRADE_DAMAGE = 4;

    public SpinKick() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        int debuffCount = 0;
        for (AbstractPower po : m.powers) {
            if (po.type == AbstractPower.PowerType.DEBUFF && !(po instanceof EmbedPower))
                debuffCount++;
        }
        atb(new AquaDrawCardAction(debuffCount));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}