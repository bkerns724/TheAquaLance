package theExile.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

public class CrushedPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(CrushedPower.class.getSimpleName());

    public CrushedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        priority = 4;
    }

    @Override
    public void atEndOfRound() {
        if (this.amount == 0)
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        else
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL)
            return damage + amount;
        else
            return damage;
    }
}