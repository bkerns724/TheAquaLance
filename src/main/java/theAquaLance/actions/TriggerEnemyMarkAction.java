package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;

import static theAquaLance.util.Wiz.*;

public class TriggerEnemyMarkAction extends AbstractGameAction {
    public TriggerEnemyMarkAction(AbstractCreature m) {
        this.target = m;
        actionType = ActionType.DAMAGE;
    }

    public void update() {
        if (target.hasPower(MarkPower.POWER_ID))
            att(new LoseHPAction(target, null, target.getPower(MarkPower.POWER_ID).amount, AttackEffect.FIRE));

        isDone = true;
    }
}
