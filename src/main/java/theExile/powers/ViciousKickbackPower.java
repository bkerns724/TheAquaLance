package theExile.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

import static theExile.util.Wiz.*;

public class ViciousKickbackPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(ViciousKickbackPower.class.getSimpleName());

    public ViciousKickbackPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        flash();
        int damAmount = amount*getDebuffCount(info.owner);
        if (damAmount > 0)
            thornDmgTop(info.owner, damAmount);
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        removePower(this);
    }
}