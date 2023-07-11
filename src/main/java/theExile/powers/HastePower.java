package theExile.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import theExile.ExileMod;

import static theExile.util.Wiz.adp;

public class HastePower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(HastePower.class.getSimpleName());

    public HastePower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new DrawCardAction(amount));
        flash();
    }
}