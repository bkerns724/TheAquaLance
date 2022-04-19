package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.applyToSelf;

public class ChargePower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(ChargePower.class.getSimpleName());

    public ChargePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (adp().energy.energy > 0)
            applyToSelf(new VigorPower(adp(), amount));
    }
}