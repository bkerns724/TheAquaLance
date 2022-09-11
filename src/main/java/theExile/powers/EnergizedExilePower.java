package theExile.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import theExile.ExileMod;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class EnergizedExilePower extends AbstractExilePower {
    public static final String POWER_ID = ExileMod.makeID(EnergizedExilePower.class.getSimpleName());

    public EnergizedExilePower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
    }

    @Override
    public void onEnergyRecharge() {
        flash();
        adp().gainEnergy(amount);
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }
}