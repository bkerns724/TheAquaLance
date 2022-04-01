package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;
import theArcanist.actions.IgnorePainAction;

import static theArcanist.util.Wiz.atb;

public class IgnorePainPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(IgnorePainPower.class.getSimpleName());

    public IgnorePainPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        for (int i = 0; i < amount; i++)
            atb(new IgnorePainAction());
    }
}