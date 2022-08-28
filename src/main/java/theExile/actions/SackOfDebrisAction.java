package theExile.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.core.Settings;
import theExile.cards.SackOfDebris;

import static theExile.util.Wiz.*;

public class SackOfDebrisAction extends AbstractGameAction {
    float startingDuration;
    SackOfDebris card;

    public SackOfDebrisAction(SackOfDebris card) {
        target = adp();
        actionType = ActionType.DAMAGE;
        duration = Settings.ACTION_DUR_FAST;
        startingDuration = duration;
        this.card = card;
    }

    public void update() {
        if (duration == startingDuration) {
            int count = adp().hand.size();
            if (count != 0) {
                for (int i = 0; i < count; i++) {
                    int x = MathUtils.random(0, 1);
                    AttackEffect effect = getSlashEffect(card.damage);

                    att(new AttackDamageRandomEnemyAction(card, effect));
                }
                discardTop(count, true);
            }

            isDone = true;
        }
    }
}