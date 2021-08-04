package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;

import static theAquaLance.util.Wiz.*;

public class RapidsAction extends AbstractGameAction {
    private static final float DURATION = 0.5F;

    public RapidsAction(int amount) {
        this.amount = amount;
        actionType = ActionType.CARD_MANIPULATION;
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION) {
            int handSize = adp().hand.size();
            if (handSize < amount)
                amount = handSize;
            if (handSize > 0) {
                att(new DiscardAction(adp(), adp(), amount, true));
                att(new DrawCardAction(amount));
            }
        }

        tickDuration();
    }
}
