package theExile.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

import static theExile.util.Wiz.*;

public class DreadfulFinalePower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(DreadfulFinalePower.class.getSimpleName());

    public DreadfulFinalePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer && adp().hand.isEmpty())
            forAllMonstersLiving(m -> applyToEnemy(m, new TempNegStrengthPower(m, amount)));
    }
}