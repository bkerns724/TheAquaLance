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
        if (m == null)
            return;
        if (duration != DEFAULT_DURATION) {
            isDone = true;
            return;
        }

        for (int x = amount; x > 0; x = x - 3)
            if (x > 2 || x < AbstractDungeon.miscRng.random(0, 2))
                doAction();

        tickDuration();
    }

    private void doAction() {
        if (m == null)
            return;
        int x;
        x = AbstractDungeon.miscRng.random(0, 6);

        switch(x) {
            case 0:
                applyToEnemyTop(m, new FrostbitePower(m, 6));
                break;
            case 1:
                applyToEnemyTop(m, new CrushedPower(m, 3));
                break;
            case 2:
                applyToEnemyTop(m, new JinxPower(m, 1));
                break;
            case 3:
                applyToEnemyTop(m, new WeakPower(m, 3, false));
                break;
            case 4:
                applyToEnemyTop(m, new VulnerablePower(m, 3, false));
                break;
            case 5:
                applyToEnemyTop(m, new StrengthPower(m, -2));
                break;
            case 6:
                att(new PewcumberAction(m, 12));
                break;
        }
    }
}
