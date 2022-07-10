package theExile.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

import static theExile.util.Wiz.removePower;

public class StoneskinPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(StoneskinPower.class.getSimpleName());

    public StoneskinPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (type != DamageInfo.DamageType.HP_LOSS)
            return damage - amount;
        else
            return damage;
    }

    @Override
    public void atStartOfTurn() {
        removePower(this);
    }
}