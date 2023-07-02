package theExile.powers;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import theExile.ExileMod;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class DiscardNextTurnPower extends AbstractExilePower {
    public static final String POWER_ID = ExileMod.makeID(DiscardNextTurnPower.class.getSimpleName());

    public DiscardNextTurnPower(int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, adp(), amount);
        priority = 7;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        atb(new DiscardAction(adp(), adp(), amount, false));
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }
}