package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import theArcanist.powers.ClownNosePower;
import theArcanist.relics.ClownNose;

import static theArcanist.util.Wiz.*;

public class ClownNoseAction extends AbstractGameAction {
    private float DURATION = 0.1f;
    private AbstractMonster targetMonster;

    public ClownNoseAction(AbstractMonster target) {
        targetMonster = target;
        duration = DURATION;
    }

    @Override
    public void update() {
        if (targetMonster.hasPower(ClownNosePower.POWER_ID)) {
            isDone = true;
            return;
        }
        if (duration == DURATION) {
            int count = 0;
            for (AbstractPower pow : targetMonster.powers)
                if (pow.type == AbstractPower.PowerType.DEBUFF && !pow.ID.equals(GainStrengthPower.POWER_ID))
                    count++;

            if (count >= ClownNose.DEBUFF_AMOUNT) {
                if (adp().hasRelic(ClownNose.ID))
                    adp().getRelic(ClownNose.ID).flash();
                applyToEnemy(targetMonster, new ClownNosePower(targetMonster));
                thornDmgTop(targetMonster, ClownNose.DAMAGE_AMOUNT, AttackEffect.SLASH_HEAVY);
            }
        }
        isDone = true;
    }
}
