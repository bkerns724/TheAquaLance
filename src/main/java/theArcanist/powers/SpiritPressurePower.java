package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.applyToEnemy;
import static theArcanist.util.Wiz.forAllMonstersLiving;

public class SpiritPressurePower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(SpiritPressurePower.class.getSimpleName());

    public SpiritPressurePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        forAllMonstersLiving(m -> applyToEnemy(m, new JinxPower(m, amount)));
    }
}