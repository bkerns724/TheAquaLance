package theExile.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

public class EldritchStaffPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(EldritchStaffPower.class.getSimpleName());

    //Code in Jinx Power
    public EldritchStaffPower(AbstractCreature owner) {
        super(POWER_ID, PowerType.BUFF, false, owner, -1);
    }
}