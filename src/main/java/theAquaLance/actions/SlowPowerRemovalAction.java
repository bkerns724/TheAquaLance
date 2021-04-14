package theAquaLance.actions;//

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.util.Wiz.atb;

public class SlowPowerRemovalAction extends AbstractGameAction {
    private static final float DURATION = 0.1F;
    private AbstractPower power;

    public SlowPowerRemovalAction(AbstractCreature target, AbstractPower power) {
        this.target = target;
        this.power = power;
        actionType = ActionType.REDUCE_POWER;
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION && target != null)
            atb(new RemoveSpecificPowerAction(target, target, power));

        tickDuration();
    }
}
