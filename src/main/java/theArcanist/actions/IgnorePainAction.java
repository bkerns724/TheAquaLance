package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theArcanist.util.Wiz.*;

public class IgnorePainAction extends AbstractGameAction {
    AbstractCard card;
    private static final float DURATION = 0.5f;

    public IgnorePainAction(AbstractCard card) {
        this.card = card;
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION) {
            AbstractDungeon.player.limbo.addToBottom(card);
            AbstractDungeon.player.drawPile.removeCard(card);
            AbstractDungeon.player.discardPile.removeCard(card);
            AbstractDungeon.getCurrRoom().souls.remove(card);
            AbstractDungeon.player.limbo.group.add(card);
            card.target_y = Settings.HEIGHT / 2.0f;
            card.target_x = Settings.WIDTH / 2.0f;
            card.targetAngle = 0;
            card.targetDrawScale = 0.8f;
            card.lighten(true);
        }
        if (duration < 0.25) {
            att(new ExhaustSpecificCardAction(card, adp().limbo));
            isDone = true;
        }

        tickDuration();
    }
}