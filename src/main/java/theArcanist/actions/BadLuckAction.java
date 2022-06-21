package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import static theArcanist.util.Wiz.adp;

public class BadLuckAction extends AbstractGameAction {

    public BadLuckAction() {
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        AbstractCard card = null;
        int cost = -3;
        int xCost = adp().energy.energy;
        for (AbstractCard handCard : adp().hand.group ) {
            if (cost == 2)
                continue;
            if (cost == -1 && xCost > cost) {
                cost = xCost;
                card = handCard;
            }
            else if (handCard.cost > cost) {
                cost = handCard.cost;
                card = handCard;
            }
        }

        isDone = true;

        if (card == null)
            return;

        adp().hand.moveToDiscardPile(card);
        card.triggerOnManualDiscard();
        GameActionManager.incrementDiscard(true);
    }
}
