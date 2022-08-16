package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.powers.CrushedPower;

import static theExile.util.Wiz.applyToEnemyTop;

public class TripleCrushedAction extends AbstractGameAction {
    private static final float DURATION = 0.2f;

    public TripleCrushedAction(AbstractMonster m) {
        target = m;
        duration = startDuration = DURATION;
    }

    @Override
    public void update() {
        if (duration == startDuration && target != null) {
            AbstractPower pow = target.getPower(CrushedPower.POWER_ID);
            int count = 0;
            if (pow != null)
                count = pow.amount;
            if (count > 0)
                applyToEnemyTop(target, new CrushedPower(target, 2 * count));
        }

        tickDuration();
    }
}
