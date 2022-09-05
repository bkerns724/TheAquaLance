package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class BlessedNecklaceAction extends AbstractGameAction {
    private int blockAmount;

    public BlessedNecklaceAction(int blockAmount) {
        this.blockAmount = blockAmount;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (duration == startDuration) {
            int count = AbstractDungeon.player.hand.size();
            if (count != 0) {
                att(new DiscardAction(adp(), adp(), count, true));
                att(new MyAddTempHPAction(adp(), adp(), count*blockAmount));
            }

            isDone = true;
        }
    }
}
