package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.applyToEnemy;
import static theArcanist.util.Wiz.forAllMonstersLiving;

public class ShockwaveSigilsPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(ShockwaveSigilsPower.class.getSimpleName());

    public ShockwaveSigilsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onDiscardSigil() {
        forAllMonstersLiving(m -> applyToEnemy(m, new CrushedPower(m, amount)));
    }
}