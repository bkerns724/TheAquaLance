package theExile.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class BlinkPower extends AbstractExilePower {
    public static String POWER_ID = makeID(BlinkPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BlinkPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        amount2 = 1;
        this.name = NAME;
        isTwoAmount = true;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        amount2++;
    }

    @Override
    public void updateDescription() {
        if (amount2 == 1)
            description = descriptionArray[0];
        else
            description = descriptionArray[1];
        description = description.replace("!A!", "#b" + amount);
        description = description.replace("!A2!", "#b" + amount2);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        atb(new GainBlockAction(adp(), amount));
        if (!adp().hasPower(HastePower.POWER_ID))
            discard(amount2);
    }
}