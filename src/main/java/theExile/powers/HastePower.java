package theExile.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.ExileMod;

import static theExile.util.Wiz.*;

public class HastePower extends AbstractExilePower {
    public static final String POWER_ID = ExileMod.makeID(HastePower.class.getSimpleName());

    public HastePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        priority = 25;  // Same as Tools of the Trade
    }

    public void atStartOfTurnPostDraw() {
        flash();
        int drawAmount = amount;
        int discardAmount = amount;
        AbstractPower drawPow = adp().getPower(DrawNextTurnPower.POWER_ID);
        if (drawPow != null)
            drawAmount += drawPow.amount;
        AbstractPower discardPow = adp().getPower(DiscardNextTurnPower.POWER_ID);
        if (discardPow != null)
            discardAmount += discardPow.amount;
        BlinkPower blinkPow = (BlinkPower)adp().getPower(BlinkPower.POWER_ID);
        if (blinkPow != null)
            discardAmount += blinkPow.amount2;
        cardDraw(drawAmount);
        discard(discardAmount);
    }
}