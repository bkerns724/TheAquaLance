package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.AquaDrawCardAction;
import theAquaLance.powers.HobbledPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class JumpKick extends AbstractEasyCard {
    public final static String ID = makeID("JumpKick");
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;

    public JumpKick() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (m.hasPower(HobbledPower.POWER_ID)) {
            atb(new GainEnergyAction(magicNumber));
            atb(new AquaDrawCardAction(magicNumber));
            atb(new DiscardAction(p, p, magicNumber, false));
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}