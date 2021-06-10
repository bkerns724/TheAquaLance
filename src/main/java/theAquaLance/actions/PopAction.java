package theAquaLance.actions;//

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.powers.EmbedPower;

public class PopAction extends AbstractGameAction {
    private static final float DURATION = 0.5F;
    private int popAmount;

    public PopAction(AbstractCreature target, int pop) {
        this.target = target;
        popAmount = pop;
        actionType = ActionType.CARD_MANIPULATION;
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION && target != null) {
            for (AbstractPower p : target.powers) {
                if (p instanceof EmbedPower) {
                    ((EmbedPower) p).onFinisher(popAmount);
                }
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        tickDuration();
    }
}
