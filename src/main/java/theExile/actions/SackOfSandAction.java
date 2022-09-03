package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.Settings;
import theExile.cards.SackOfSand;

import static theExile.util.Wiz.*;

public class SackOfSandAction extends AbstractGameAction {
    float startingDuration;
    SackOfSand card;

    public SackOfSandAction(SackOfSand card) {
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
                int temp = card.baseBlock;
                card.baseBlock = count*card.magicNumber;
                card.applyPowers();
                att(new GainBlockAction(adp(), card.block));
                card.baseBlock = temp;
                card.applyPowers();
                discardTop(count, true);
            }

            isDone = true;
        }
    }
}