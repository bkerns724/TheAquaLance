package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;

public class BanePower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(BanePower.class.getSimpleName());

    // Relevant code in JinxPower
    public BanePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
    }
}