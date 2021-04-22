package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import theAquaLance.AquaLanceMod;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static theAquaLance.util.Wiz.*;

public class DiscardNextTurnPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.modID + "DiscardNextTurn";
    public DiscardNextTurnPower(AbstractCreature owner, int amount) {
        super("", PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        atb(new DiscardAction(adp(), adp(), amount, false));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
    }
}