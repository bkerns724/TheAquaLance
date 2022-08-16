package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theExile.cards.AbstractExileCard;

import static theExile.util.Wiz.*;
import static theExile.util.Wiz.att;

public class PlayTopCardElegantAction extends AbstractGameAction {

    public PlayTopCardElegantAction() {
        duration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.WAIT;
    }

    @Override
    public void update() {
        isDone = true;

        if (adp().drawPile.size() + adp().discardPile.size() == 0)
            return;

        if (AbstractDungeon.player.drawPile.isEmpty()) {
            att(new PlayTopCardElegantAction());
            att(new EmptyDeckShuffleAction());
            return;
        }

        if (!AbstractDungeon.player.drawPile.isEmpty()) {
            AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
            adp().drawPile.group.remove(card);
            adRoom().souls.remove(card);
            card.exhaustOnUseOnce = false;
            adp().limbo.group.add(card);
            card.current_y = -200.0F * Settings.scale;
            card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            card.target_y = (float)Settings.HEIGHT / 2.0F;
            card.targetAngle = 0.0F;
            card.lighten(false);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;
            if (card instanceof AbstractExileCard)
                ((AbstractExileCard) card).elegant = true;
            card.applyPowers();
            target = AbstractDungeon.getMonsters().getRandomMonster(null,true,
                    AbstractDungeon.cardRandomRng);
            att(new NewQueueCardAction(card, target, false, true));
            att(new UnlimboAction(card));
            if (!Settings.FAST_MODE)
                att(new WaitAction(Settings.ACTION_DUR_MED));
            else
                att(new WaitAction(Settings.ACTION_DUR_FASTER));
        }

    }
}
