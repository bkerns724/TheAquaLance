package theArcanist.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.ArcanistMod;
import theArcanist.actions.ChaosFormAction;

import static theArcanist.util.Wiz.atb;

public class ChaosFormPower extends AbstractArcanistPower implements OnReceivePowerPower {
    public static String POWER_ID = ArcanistMod.makeID(ChaosFormPower.class.getSimpleName());

    private int trueAmount;

    public ChaosFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        trueAmount = amount;
        this.amount = trueAmount / 3;
        amount2 = trueAmount % 3;
        isTwoAmount = true;
        updateDescription();
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (pow instanceof ChaosFormPower && target == owner) {
            trueAmount += ((ChaosFormPower) pow).trueAmount;
            amount = trueAmount / 3;
            amount2 = trueAmount % 3;
        }
        updateDescription();
        return true;
    }

    @Override
    public void stackPower(int stackAmount) {
    }

    @Override
    public void atStartOfTurn() {
        atb(new ChaosFormAction(trueAmount));
    }
}