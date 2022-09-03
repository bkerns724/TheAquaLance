package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theExile.cards.ConstantPractice;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class ConstantPracticeAction extends AbstractGameAction {
    public static final String[] TEXT;

    public ConstantPracticeAction() {
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == startDuration) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            tmp.group.addAll(adp().discardPile.group);
            tmp.group.removeIf(c -> c instanceof ConstantPractice);
            if (!tmp.isEmpty()) {
                if (tmp.size() == 1) {
                    AbstractCard cardToMove = tmp.getTopCard();
                    cardToMove.upgrade();
                    if (adp().hand.size() < 10) {
                        adp().discardPile.moveToHand(cardToMove);
                        if (cardToMove.cost > 0)
                            cardToMove.cost = 0;
                        if (cardToMove.costForTurn > 0)
                            cardToMove.costForTurn = 0;
                        cardToMove.lighten(false);
                    } else
                        cardToMove.cost = 0;
                    isDone = true;
                } else {
                    AbstractDungeon.gridSelectScreen.open(tmp, 1, TEXT[0], false);
                    tickDuration();
                }
            } else
                isDone = true;
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                card.upgrade();
                if (adp().hand.size() < 10) {
                    adp().discardPile.moveToHand(card);
                    if (card.cost > 0)
                        card.cost = 0;
                    if (card.costForTurn > 0)
                        card.costForTurn = 0;
                    card.lighten(false);
                } else {
                    if (card.cost > 0)
                        card.cost = 0;
                }

                for (AbstractCard card2 : adp().discardPile.group) {
                    card2.target_y = 0f;
                    card2.unhover();
                    card2.target_x = (float) CardGroup.DISCARD_PILE_X;
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                adp().hand.refreshHandLayout();
            }

            tickDuration();
            if (isDone)
                for (AbstractCard card : adp().hand.group)
                    card.applyPowers();
        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString(makeID("ConstantPracticeAction")).TEXT;
    }
}
