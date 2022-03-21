package theArcanist.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.removePower;

public class StoneskinPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(StoneskinPower.class.getSimpleName());

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