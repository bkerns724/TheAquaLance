package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static theAquaLance.util.Wiz.*;

public class FindAndExhaustAction extends AbstractGameAction {
    private static final float DURATION = 0.5F;
    private AbstractCard card;

    public FindAndExhaustAction(AbstractCard card) {
        this.card = card;
        actionType = ActionType.CARD_MANIPULATION;
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION) {
            if (adp().hand.contains(card))
                att(new ExhaustSpecificCardAction(card, adp().hand, true));
            else if (adp().discardPile.contains(card))
                att(new ExhaustSpecificCardAction(card, adp().discardPile, true));
            else if (adp().drawPile.contains(card))
                att(new ExhaustSpecificCardAction(card, adp().drawPile, true));
        }

        tickDuration();
    }
}
