package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.atb;

public class DecayingPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(DecayingPower.class.getSimpleName());

    public DecayingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        atb(new LoseHPAction(owner, AbstractDungeon.player, amount));
    }
}