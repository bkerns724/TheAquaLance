package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static theExile.util.Wiz.adp;

public class ConstantPracticeAction extends AbstractGameAction {
    public static final String[] TEXT;
    private final int numberOfCards;

    public ConstantPracticeAction(int numberOfCards) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        this.numberOfCards = numberOfCards;
    }

    public void update() {
        if (duration == startDuration) {
            if (!adp().discardPile.isEmpty() && numberOfCards > 0) {
                if (adp().discardPile.size() <= numberOfCards) {
                    ArrayList<AbstractCard> cardsToMove = new ArrayList(adp().discardPile.group);

                    for (AbstractCard card : cardsToMove) {
                        if (adp().hand.size() < 10) {
                            adp().hand.addToHand(card);
                            card.cost = card.cost - 1;
                            adp().discardPile.removeCard(card);
                            card.lighten(false);
                            card.applyPowers();
                        } else
                            card.cost = card.cost -1;
                    }

                    isDone = true;
                } else {
                    if (numberOfCards == 1)
                        AbstractDungeon.gridSelectScreen.open(adp().discardPile, numberOfCards, TEXT[0], false);
                    else
                        AbstractDungeon.gridSelectScreen.open(adp().discardPile, numberOfCards, TEXT[1] + numberOfCards + TEXT[2], false);

                    tickDuration();
                }
            } else
                isDone = true;
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {

                for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                    if (adp().hand.size() < 10) {
                        adp().hand.addToHand(card);
                        card.cost = card.cost - 1;
                        adp().discardPile.removeCard(card);
                        card.lighten(false);
                        card.applyPowers();
                    } else
                        card.cost = card.cost -1;
                }

                for (AbstractCard card : adp().discardPile.group) {
                    card.target_y = 0f;
                    card.unhover();
                    card.target_x = (float) CardGroup.DISCARD_PILE_X;
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
        TEXT = CardCrawlGame.languagePack.getUIString("ConstantPracticeAction").TEXT;
    }
}
