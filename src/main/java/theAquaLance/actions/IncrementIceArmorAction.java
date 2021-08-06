package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.powers.IceArmorPower;

import static theAquaLance.util.Wiz.*;

public class IncrementIceArmorAction extends AbstractGameAction {
    private static final float DURATION = 0.1F;

    public IncrementIceArmorAction(int amount) {
        this.amount =amount;
        actionType = ActionType.POWER;
        duration = DURATION;
    }

    public void update() {
        AbstractPower pow = adp().getPower(IceArmorPower.POWER_ID);
        if (pow != null) {
            ((IceArmorPower) pow).mult += amount;
            pow.updateDescription();
        }
        isDone = true;
    }
}
