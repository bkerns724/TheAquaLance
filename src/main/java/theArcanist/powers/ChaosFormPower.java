package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;
import theArcanist.actions.ChaosFormAction;

import static theArcanist.util.Wiz.atb;

public class ChaosFormPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(ChaosFormPower.class.getSimpleName());

    public ChaosFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        atb(new ChaosFormAction(amount));
    }
}