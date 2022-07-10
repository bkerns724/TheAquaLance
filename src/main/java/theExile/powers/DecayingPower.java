package theExile.powers;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theExile.ExileMod;

import static theExile.util.Wiz.atb;

public class DecayingPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(DecayingPower.class.getSimpleName());

    public DecayingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        atb(new LoseHPAction(owner, AbstractDungeon.player, amount));
    }
}