package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import theAquaLance.AquaLanceMod;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theAquaLance.util.Wiz.*;

public class DiscardNextNextTurnPower extends AbstractEasyPower {
    public static final String POWER_ID = AquaLanceMod.makeID("DiscardTwoTurns");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DiscardNextNextTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        priority = 31;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        int amount2 = amount;
        applyToSelf(new DiscardNextTurnPower(adp(), amount2));
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }


    @Override
    public void updateDescription() {
        // May need to adjust this
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}