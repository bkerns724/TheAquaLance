package theAquaLance.actions;//

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import theAquaLance.AquaLanceMod;
import theAquaLance.powers.StreamlinedPower;

import static theAquaLance.util.Wiz.adp;
import static theAquaLance.util.Wiz.att;

public class AquaGainEnergyAction extends AbstractGameAction {
    private static final float DURATION = 0.1F;
    private int amount;

    public AquaGainEnergyAction(int amount) {
        actionType = ActionType.ENERGY;
        this.amount = amount;
        duration = DURATION;
    }

    public void update() {
        if (adp().hasPower(StreamlinedPower.POWER_ID)) {
            att(new GainEnergyAction(amount + 1));
            att(new ReducePowerAction(adp(), adp(), StreamlinedPower.POWER_ID, 1));
        }
        else
            att(new GainEnergyAction(amount));

        tickDuration();

        isDone = true;
    }
}
