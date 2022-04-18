package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;
import theArcanist.actions.ChaosAuraAction;

import static theArcanist.util.Wiz.atb;
import static theArcanist.util.Wiz.forAllMonstersLiving;

public class ChaosAuraPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(ChaosAuraPower.class.getSimpleName());

    public ChaosAuraPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        forAllMonstersLiving(m -> atb(new ChaosAuraAction(m, amount)));
    }
}