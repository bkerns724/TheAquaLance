package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theArcanist.util.Wiz.*;

public class IgnorePainAction extends AbstractGameAction {
    private AbstractCard cardToExhaust = null;
    private static final float DURATION = 0.35f;

    public IgnorePainAction() {
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION) {
            cardToExhaust = getCardToExhaust();

            if (cardToExhaust == null) {
                isDone = true;
                return;
            }

            AbstractDungeon.player.limbo.addToBottom(cardToExhaust);
            AbstractDungeon.player.drawPile.removeCard(cardToExhaust);
            AbstractDungeon.player.discardPile.removeCard(cardToExhaust);
            AbstractDungeon.player.hand.removeCard(cardToExhaust);
            AbstractDungeon.getCurrRoom().souls.remove(cardToExhaust);
            AbstractDungeon.player.limbo.group.add(cardToExhaust);
            cardToExhaust.target_y = Settings.HEIGHT / 2.0f;
            cardToExhaust.target_x = Settings.WIDTH / 2.0f;
            cardToExhaust.targetAngle = 0;
            cardToExhaust.targetDrawScale = 0.8f;
            cardToExhaust.lighten(true);
        }

        if (duration < 0.25) {
            att(new ExhaustSpecificCardAction(cardToExhaust, adp().limbo));
            isDone = true;
        }

        tickDuration();
    }

    private AbstractCard getCardToExhaust() {
        for (AbstractCard card : adp().drawPile.group)
            if (card.type == AbstractCard.CardType.STATUS)
                return card;
        for (AbstractCard card : adp().discardPile.group)
            if (card.type == AbstractCard.CardType.STATUS)
                return card;
        for (AbstractCard card : adp().hand.group)
            if (card.type == AbstractCard.CardType.STATUS)
                return card;

        return null;
    }
}