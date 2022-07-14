package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class DiscardToDoAction extends AbstractGameAction {
    private static final float DURATION = 0.1F;
    private final AbstractGameAction action;
    private final boolean isRandom;

    public DiscardToDoAction(int amount, AbstractGameAction action, boolean isRandom) {
        this.amount = amount;
        this.action = action;
        this.isRandom = isRandom;
        actionType = ActionType.CARD_MANIPULATION;
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION) {
            int handSize = adp().hand.size();
            if (handSize >= amount) {
                att(action);
                att(new DiscardAction(adp(), adp(), amount, isRandom));
            }
            else if (handSize > 0) {
                amount = handSize;
                att(new DiscardAction(adp(), adp(), amount, isRandom));
            }
            isDone = true;
        }

        tickDuration();
    }
}
