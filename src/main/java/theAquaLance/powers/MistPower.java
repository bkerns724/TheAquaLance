package theAquaLance.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theAquaLance.AquaLanceMod;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static theAquaLance.util.Wiz.*;

public class MistPower extends AbstractEasyPower {
    public static final String POWER_ID = AquaLanceMod.makeID("Mist");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int actualValue;

    public MistPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        actualValue = amount;
    }

    // code is in AbstractEasyCard.triggerOnManualDiscard
    public void reduceActualValue() {
        actualValue--;
    }

    public int getActualValue() {
        return actualValue;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        actualValue += stackAmount;
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}