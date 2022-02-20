package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import static theArcanist.util.Wiz.*;

public class IncreaseMaxHPAction extends AbstractGameAction {
    private static final float DURATION = 0.5F;

    public IncreaseMaxHPAction(int amount) {
        this.amount = amount;
        actionType = ActionType.HEAL;
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION) {
            adp().increaseMaxHp(amount, false);
            isDone = true;
        }

        tickDuration();
    }
}
