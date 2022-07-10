package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theExile.util.Wiz.adp;

public class ReturnToHandAction extends AbstractGameAction {
    private AbstractCard card;

    public ReturnToHandAction(AbstractCard card) {
        actionType = ActionType.CARD_MANIPULATION;
        this.card = card;
        duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (adp().discardPile.contains(card) && adp().hand.size() < 10) {
                adp().hand.addToHand(card);
                card.unhover();
                card.setAngle(0.0F, true);
                card.lighten(false);
                card.drawScale = 0.12F;
                card.targetDrawScale = 0.75F;
                card.applyPowers();
                adp().discardPile.removeCard(card);
            } else if (adp().drawPile.contains(card) && AbstractDungeon.player.hand.size() < 10) {
                adp().hand.addToHand(card);
                card.unhover();
                card.setAngle(0.0F, true);
                card.lighten(false);
                card.drawScale = 0.12F;
                card.targetDrawScale = 0.75F;
                card.applyPowers();
                adp().drawPile.removeCard(card);
            }

            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.glowCheck();
        }

        tickDuration();
        isDone = true;
    }
}
