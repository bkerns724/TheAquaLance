package theExile.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

public class BanePower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(BanePower.class.getSimpleName());

    // Relevant code in JinxPower
    public BanePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
    }
}