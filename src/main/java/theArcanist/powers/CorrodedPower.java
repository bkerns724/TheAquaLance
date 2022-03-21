package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.atb;

// Relevant code in VulnerablePowerPatch
public class CorrodedPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(CorrodedPower.class.getSimpleName());
    public static final float VULN_INCREASE = 0.5f;

    public CorrodedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, true, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (amount == 1)
            atb(new RemoveSpecificPowerAction(owner, owner, this));
        else
            atb(new ReducePowerAction(owner, owner, POWER_ID, 1));
    }
}