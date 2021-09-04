package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.powers.IntelligencePower;
import theAquaLance.powers.SoakedPower;
import theAquaLance.relics.PaperPhish;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class EnchantedSpear extends AbstractEasyCard {
    public final static String ID = makeID("EnchantedSpear");
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 1;

    public EnchantedSpear() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        AbstractPower pow = adp().getPower(IntelligencePower.POWER_ID);
        if (pow != null)
            baseDamage += pow.amount;
        super.calculateCardDamage(mo);
        pow = mo.getPower(SoakedPower.POWER_ID);
        if (pow != null) {
            if (adp().hasRelic(PaperPhish.ID))
                damage *= 1.75; // I'm using magic numbers I'm evil
            else
                damage *= 1.5;
        }

        baseDamage = temp;
    }

    @Override
    public void applyPowers() {
        int temp = baseDamage;
        AbstractPower pow = adp().getPower(IntelligencePower.POWER_ID);
        if (pow != null)
            baseDamage += pow.amount;
        super.applyPowers();
        baseDamage = temp;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}