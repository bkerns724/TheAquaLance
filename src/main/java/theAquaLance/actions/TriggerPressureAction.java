package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.powers.PressurePower;

import static theAquaLance.util.Wiz.*;

public class TriggerPressureAction extends AbstractGameAction {
    private AbstractMonster m;
    private final static float DURATION = 0.1F;

    public TriggerPressureAction(AbstractMonster m) {
        this.m = m;
        actionType = ActionType.DAMAGE;
        duration = DURATION;
    }

    public void update() {
        if (m != null && m.hasPower(PressurePower.POWER_ID))
            att(new LoseHPAction(m, adp(), m.getPower(PressurePower.POWER_ID).amount));
        isDone = true;
    }
}
