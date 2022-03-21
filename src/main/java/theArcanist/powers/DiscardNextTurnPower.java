package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.atb;

public class DiscardNextTurnPower extends AbstractArcanistPower {
    public static final String POWER_ID = ArcanistMod.makeID(DiscardNextTurnPower.class.getSimpleName());

    public DiscardNextTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        priority = 7;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (!adp().hasPower(SpeedPower.POWER_ID))
            atb(new DiscardAction(adp(), adp(), amount, false));
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }
}