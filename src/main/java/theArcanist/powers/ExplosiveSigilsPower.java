package theArcanist.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.att;
import static theArcanist.util.Wiz.forAllMonstersLiving;

public class ExplosiveSigilsPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(ExplosiveSigilsPower.class.getSimpleName());

    public ExplosiveSigilsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onDiscardSigil() {
        forAllMonstersLiving(m -> att(new LoseHPAction(m, owner, amount, AbstractGameAction.AttackEffect.FIRE)));
    }
}