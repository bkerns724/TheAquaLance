package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ReduceWithoutRemovePowerAction extends AbstractGameAction {
    private static final float DURATION = 0.1f;
    private final AbstractPower pow;

    public ReduceWithoutRemovePowerAction(AbstractPower pow) {
        duration = startDuration = DURATION;
        this.pow = pow;
    }

    public void update() {
        if (duration == startDuration) {
            if (pow != null) {
                if (pow.amount > 0)
                    pow.reducePower(1);
                pow.updateDescription();
                AbstractDungeon.onModifyPower();
            }
        }

        tickDuration();
    }
}
