package theExile.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class SpiritPressurePower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(SpiritPressurePower.class.getSimpleName());

    public SpiritPressurePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        forAllMonstersLiving(m -> applyToEnemy(m, new JinxPower(m, amount)));
    }
}