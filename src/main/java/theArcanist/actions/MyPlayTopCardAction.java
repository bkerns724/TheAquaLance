package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theArcanist.util.Wiz.*;

// Because regular PlayTopCardAction doesn't call NewQueueCardAction with random target set to true
// No, it picks a random monster and then passes it as the target
// This is bad because the target can be dead by the time the card is actually played
// Picking the target should be lazy, not eager
// I would patch it, but then my character mod would change stuff on other characters and that would be bad

public class MyPlayTopCardAction extends AbstractGameAction {
    private boolean exhaustCards;

    public MyPlayTopCardAction(boolean exhausts) {
        duration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.WAIT;
        source = adp();
        exhaustCards = exhausts;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (adp().drawPile.size() + adp().discardPile.size() == 0) {
                isDone = true;
                return;
            }

            if (adp().drawPile.isEmpty()) {
                addToTop(new MyPlayTopCardAction(exhaustCards));
                addToTop(new EmptyDeckShuffleAction());
                isDone = true;
                return;
            }

            if (!adp().drawPile.isEmpty()) {
                AbstractCard card = adp().drawPile.getTopCard();
                adp().drawPile.group.remove(card);
                AbstractDungeon.getCurrRoom().souls.remove(card);
                card.exhaustOnUseOnce = exhaustCards;
                adp().limbo.group.add(card);
                card.current_y = -200.0F * Settings.scale;
                card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                card.target_y = (float)Settings.HEIGHT / 2.0F;
                card.targetAngle = 0.0F;
                card.lighten(false);
                card.drawScale = 0.12F;
                card.targetDrawScale = 0.75F;
                card.applyPowers();
                att(new NewQueueCardAction(card, true, false, true));
                att(new UnlimboAction(card));
                if (!Settings.FAST_MODE) {
                    att(new WaitAction(Settings.ACTION_DUR_MED));
                } else {
                    att(new WaitAction(Settings.ACTION_DUR_FASTER));
                }
            }

            isDone = true;
        }

    }
}
