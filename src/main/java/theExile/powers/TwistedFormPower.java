package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.ExileMod;
import theExile.actions.TwistedFormAction;

import static theExile.util.Wiz.atb;

public class TwistedFormPower extends AbstractExilePower implements OnReceivePowerPower {
    public static String POWER_ID = ExileMod.makeID(TwistedFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TwistedFormPower(AbstractCreature owner, int amount, int amount2) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
        isTwoAmount = true;
        this.amount2 = amount2;
        updateDescription();
    }

    public void addToList() {
        if (owner.powers.contains(this))
            ExileMod.twistedList.add(this);
    }

    public void deathCheck() {
        if (owner == null || owner.isDead)
            ExileMod.twistedList.remove(this);
        else if (!owner.powers.contains(this))
            owner.powers.add(this);
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        if (pow instanceof TwistedFormPower && this != pow) {
            amount += pow.amount;
            amount2 += ((TwistedFormPower) pow).amount2;
        }
        return true;
    }

    @Override
    public void stackPower(int stackAmount) { }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (owner.currentHealth > 0 && !isPlayer)
            atb(new TwistedFormAction(owner, amount, amount2));
    }
}