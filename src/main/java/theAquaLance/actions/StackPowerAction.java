package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theAquaLance.util.Wiz.*;

public class StackPowerAction extends AbstractGameAction {
    private static final float DURATION = 0.01F;
    private AbstractPower power;

    public StackPowerAction(AbstractPower power, int amount) {
        this.power = power;
        this.amount = amount;
        actionType = ActionType.POWER;
        duration = DURATION;
    }

    public void update() {
        if (power != null)
            power.stackPower(amount);
        isDone = true;
    }
}
