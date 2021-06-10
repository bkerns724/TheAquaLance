package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theAquaLance.AquaLanceMod;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static theAquaLance.util.Wiz.*;

public class DrawNextNextTurnPower extends AbstractEasyPower {
    public static final String POWER_ID = AquaLanceMod.makeID("DrawTwoTurns");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DrawNextNextTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        priority = 30;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        applyToSelf(new DrawNextTurnPower(adp(), amount));
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}