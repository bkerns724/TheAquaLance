package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.adp;

public class HastePower extends AbstractArcanistPower {
    public static final String POWER_ID = ArcanistMod.makeID(HastePower.class.getSimpleName());

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
        addToBot(new DrawCardAction(owner, drawAmount));
        addToBot(new DiscardAction(owner, owner, discardAmount, false));
    }
}