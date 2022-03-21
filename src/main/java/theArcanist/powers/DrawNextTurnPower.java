package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.atb;

public class DrawNextTurnPower extends AbstractArcanistPower {
    public static final String POWER_ID = ArcanistMod.makeID(DrawNextTurnPower.class.getSimpleName());

    public DrawNextTurnPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        priority = 6;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (!adp().hasPower(SpeedPower.POWER_ID))
            atb(new DrawCardAction(amount));
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }
}