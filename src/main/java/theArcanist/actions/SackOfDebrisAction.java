package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import theArcanist.cards.SackOfDebris;

import static theArcanist.util.Wiz.*;

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
                for (int i = 0; i < count; i++)
                    att(new DamageRandomEnemyAction(new DamageInfo(adp(), card.damage, DamageInfo.DamageType.NORMAL),
                            AttackEffect.SLASH_DIAGONAL));
                discardTop(count, true);
            }

            isDone = true;
        }
    }
}