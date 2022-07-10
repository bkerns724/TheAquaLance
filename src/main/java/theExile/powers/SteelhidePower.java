package theExile.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

public class SteelhidePower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(SteelhidePower.class.getSimpleName());

    public SteelhidePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (type != DamageInfo.DamageType.HP_LOSS)
            return damage - amount;
        else
            return damage;
    }
}