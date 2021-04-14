package theAquaLance.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theAquaLance.AquaLanceMod;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theAquaLance.actions.AquaDrawCardAction;

import static theAquaLance.util.Wiz.*;

public class AquaDrawNextTurnPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("DrawNextTurn");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AquaDrawNextTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    public void atStartOfTurnPostDraw() {
        flash();
        addToBot(new AquaDrawCardAction(amount));
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }


    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}