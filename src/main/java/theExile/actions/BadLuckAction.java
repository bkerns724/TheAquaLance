package theExile.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;

import static theExile.util.Wiz.adp;

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
            if (handCard.cost == -2)
                continue;
            if (handCard.cost == -1 && xCost > cost)
                cost = xCost;
            else if (handCard.cost > cost)
                cost = handCard.cost;
        }

        if (cost < 0) {
            isDone = true;
            return;
        }

        ArrayList<AbstractCard> cards = new ArrayList<>();
        for (AbstractCard handCard : adp().hand.group)
            if (handCard.cost == cost)
                cards.add(handCard);

        if (cards.size() == 0) {
            isDone = true;
            return;
        }

        int x = MathUtils.random(0, cards.size() - 1);
        card = cards.get(x);

        isDone = true;

        adp().hand.moveToDiscardPile(card);
        card.triggerOnManualDiscard();
        GameActionManager.incrementDiscard(true);
    }
}
