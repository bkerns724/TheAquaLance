package theArcanist.actions;

import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;

public class MyAddTempHPAction extends AbstractGameAction {
    private static final float DURATION = 0.1f;

    public MyAddTempHPAction(AbstractCreature target, AbstractCreature source, int amount) {
        setValues(target, source, amount);
        actionType = ActionType.HEAL;
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION) {
            TempHPField.tempHp.set(target, TempHPField.tempHp.get(target) + amount);
            if (amount > 0) {
                AbstractDungeon.effectsQueue.add(new HealEffect(target.hb.cX - target.animX, target.hb.cY, amount));
                target.healthBarUpdatedEvent();
            }
        }

        tickDuration();
    }
}