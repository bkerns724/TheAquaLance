package theExile.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

import static theExile.util.Wiz.atb;

public class CrushedPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(CrushedPower.class.getSimpleName());

    public CrushedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        priority = 4;
    }

    @Override
    public void atEndOfRound() {
        atb(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL)
            return damage + amount;
        else
            return damage;
    }
}