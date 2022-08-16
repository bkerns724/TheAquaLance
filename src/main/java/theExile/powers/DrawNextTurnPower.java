package theExile.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import theExile.ExileMod;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class DrawNextTurnPower extends AbstractExilePower {
    public static final String POWER_ID = ExileMod.makeID(DrawNextTurnPower.class.getSimpleName());

    public DrawNextTurnPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        priority = 6;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        atb(new DrawCardAction(amount));
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }
}