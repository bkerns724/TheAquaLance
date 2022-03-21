package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.atb;

public class EnergizedArcanistPower extends AbstractArcanistPower {
    public static final String POWER_ID = ArcanistMod.makeID(EnergizedArcanistPower.class.getSimpleName());

    public EnergizedArcanistPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
    }

    @Override
    public void onEnergyRecharge() {
        flash();
        adp().gainEnergy(amount);
        atb(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }
}