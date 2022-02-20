package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ExplosivePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theArcanist.powers.CrushedPower;
import theArcanist.powers.FrostbitePower;
import theArcanist.powers.JinxPower;

import static theArcanist.util.Wiz.*;

public class ChaosAuraAction extends AbstractGameAction {
    private AbstractMonster m;
    private static int EXPLODE_THRESHOLD = 30;

    public ChaosAuraAction(AbstractMonster m, int amount)
    {
        duration = DEFAULT_DURATION;
        this.amount = amount;
        this.m = m;
    }

    @Override
    public void update() {
        if (duration != DEFAULT_DURATION) {
            isDone = true;
            return;
        }

        for (int x = amount; x > 0; x = x - 3)
            if (x > 2 || x <= AbstractDungeon.miscRng.random(1, 3))
                doAction(false);

        tickDuration();
    }

    private void doAction(boolean simple) {
        int x;
        if (!simple)
            x = AbstractDungeon.miscRng.random(0, 7);
        else
            x = AbstractDungeon.miscRng.random(0, 6);

        switch(x) {
            case 0:
                applyToEnemy(m, new FrostbitePower(m, 6));
                break;
            case 1:
                applyToEnemy(m, new CrushedPower(m, 3));
                break;
            case 2:
                applyToEnemy(m, new JinxPower(m, 1));
                break;
            case 3:
                applyToEnemy(m, new WeakPower(m, 3, false));
                break;
            case 4:
                applyToEnemy(m, new VulnerablePower(m, 3, false));
                break;
            case 5:
                applyToEnemy(m, new StrengthPower(m, -2));
                break;
            case 6:
                atb(new PewcumberAction(m, 12));
                break;
            case 7:
                if (m.currentHealth <= EXPLODE_THRESHOLD)
                    // 1 is duration, not damage
                    applyToEnemy(m, new ExplosivePower(m, 1));
                else
                    doAction(true);
                break;
        }
    }
}
