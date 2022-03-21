package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;

public class EldritchStaffPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(EldritchStaffPower.class.getSimpleName());

    public EldritchStaffPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }
}