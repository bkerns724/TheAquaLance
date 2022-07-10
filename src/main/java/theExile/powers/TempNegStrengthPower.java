package theExile.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

import static theExile.util.Wiz.atb;

public class TempNegStrengthPower extends AbstractExilePower {
    public static final String POWER_ID = ExileMod.makeID(TempNegStrengthPower.class.getSimpleName());

    public TempNegStrengthPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        float output = damage - (float)amount;
        if (output < 0)
            output = 0;
        return type == DamageInfo.DamageType.NORMAL ? output : damage;
    }

    @Override
    public void atEndOfRound() {
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }
}