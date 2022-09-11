package theExile.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

public class CrushedPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(CrushedPower.class.getSimpleName());

    public CrushedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        priority = 6;
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL)
            return damage + amount;
        else
            return damage;
    }
}