package theAquaLance.actions;//

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import theAquaLance.powers.StreamlinedPower;

import static theAquaLance.util.Wiz.*;

public class AquaDrawCardAction extends AbstractGameAction {
    private static final float DURATION = 0.1F;
    private int amount;

    public AquaDrawCardAction(int amount) {
        actionType = ActionType.DRAW;
        this.amount = amount;
        duration = DURATION;
    }

    public void update() {
        if (adp().hasPower(StreamlinedPower.POWER_ID)) {
            att(new DrawCardAction(amount + 1));
            att(new ReducePowerAction(adp(), adp(), StreamlinedPower.POWER_ID, 1));
        }
        else
            att(new DrawCardAction(amount));

        tickDuration();

        isDone = true;
    }
}
