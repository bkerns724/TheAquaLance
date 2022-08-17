package theExile.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static theExile.util.Wiz.*;

public class PlayRandomCardDiscardAction extends AbstractGameAction {

    public PlayRandomCardDiscardAction() {
        duration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.WAIT;
    }

    @Override
    public void update() {
        isDone = true;

        AbstractCard card = getRandomCard();
        if (card == null)
            return;

        if (!AbstractDungeon.player.discardPile.isEmpty()) {
            target = null;
            if (card.target == AbstractCard.CardTarget.ENEMY)
                target = getTarget(card);
            adp().discardPile.group.remove(card);
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
            card.applyPowers();
            att(new NewQueueCardAction(card, target, false, true));
            att(new UnlimboAction(card));
            if (!Settings.FAST_MODE)
                att(new WaitAction(Settings.ACTION_DUR_MED));
            else
                att(new WaitAction(Settings.ACTION_DUR_FASTER));
        }
    }

    AbstractCard getRandomCard() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        for (AbstractCard card : adp().discardPile.group) {
            for (AbstractMonster m : getEnemies()) {
                if (card.canUse(adp(), m)) {
                    list.add(card);
                    break;
                }
            }
        }
        if (list.size() == 0)
            return null;
        int x = MathUtils.random(0, list.size() - 1);
        return list.get(x);
    }

    AbstractMonster getTarget(AbstractCard card) {
        ArrayList<AbstractMonster> list = new ArrayList<>();
        for (AbstractMonster m : getEnemies())
            if (card.canUse(adp(), m))
                list.add(m);
        int x = MathUtils.random(0, list.size() - 1);
        return list.get(x);
    }
}
