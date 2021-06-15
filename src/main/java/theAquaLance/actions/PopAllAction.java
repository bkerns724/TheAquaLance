package theAquaLance.actions;//

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.powers.EmbedPower;

public class PopAllAction extends AbstractGameAction {
    private static final float DURATION = 0.5F;

    public PopAllAction(AbstractCreature target) {
        this.target = target;
        actionType = ActionType.CARD_MANIPULATION;
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION && target != null) {
            for (AbstractPower p : target.powers) {
                if (p instanceof EmbedPower)
                    ((EmbedPower) p).popAll();
            }
        }

        tickDuration();
    }
}
