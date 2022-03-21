package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class DreadfulFinalePower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(DreadfulFinalePower.class.getSimpleName());

    public DreadfulFinalePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer && adp().hand.isEmpty())
            forAllMonstersLiving(m -> applyToEnemy(m, new TempNegStrengthPower(m, amount)));
    }
}