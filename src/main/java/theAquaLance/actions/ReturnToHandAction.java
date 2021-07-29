package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theAquaLance.util.Wiz.adp;

public class ReturnToHandAction extends AbstractGameAction {
    private AbstractCard card;
    private static final float DURATION = 0.5F;

    public ReturnToHandAction(AbstractCard card) {
        this.card = card;
        actionType = ActionType.CARD_MANIPULATION;
        duration = DURATION;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            isDone = true;
        } else if (duration == DURATION) {
            if (adp().discardPile.contains(card)) {
                adp().discardPile.removeCard(card);
                adp().discardPile.moveToHand(card);
            }
            else if (adp().drawPile.contains(card)) {
                adp().drawPile.removeCard(card);
                adp().drawPile.moveToHand(card);
            }

            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
    }
}
