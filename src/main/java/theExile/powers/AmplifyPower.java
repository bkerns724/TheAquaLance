package theExile.powers;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class AmplifyPower extends AbstractExilePower {
    public static String POWER_ID = makeID(AmplifyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AmplifyPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
        isTwoAmount = true;
        amount2 = 1;
        priority = 7;
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        amount2++;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        int count = amount2;
        if (adp().hasPower(DiscardNextTurnPower.POWER_ID))
            count += adp().getPower(DiscardNextTurnPower.POWER_ID).amount;
        atb(new DiscardAction(adp(), adp(), count, false));
        if (adp().hasPower(ResonatingPower.POWER_ID))
            adp().getPower(ResonatingPower.POWER_ID).amount += amount;
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
}