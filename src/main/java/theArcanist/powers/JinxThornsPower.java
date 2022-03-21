package theArcanist.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class JinxThornsPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(JinxThornsPower.class.getSimpleName());

    public JinxThornsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        flash();
        int damAmount = amount*getJinxAmount(info.owner);
        if (damAmount > 0)
            thornDmg(info.owner, damAmount);
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        removePower(this);
    }
}