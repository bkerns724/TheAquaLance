package theExile.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import theExile.ExileMod;
import theExile.patches.AvoidInfiniteLoopPatch;

import static theExile.util.Wiz.att;

public class SadisticGleePower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(SadisticGleePower.class.getSimpleName());

    public SadisticGleePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == PowerType.DEBUFF && source == owner && target != owner
                && !power.ID.equals(GainStrengthPower.POWER_ID) && !target.hasPower(ArtifactPower.POWER_ID)
                && !AvoidInfiniteLoopPatch.ReactFields.reaction.get(power))
            att(new GainBlockAction(owner, amount, true));
    }
}