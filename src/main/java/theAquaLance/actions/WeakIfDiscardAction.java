package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theAquaLance.util.Wiz.*;

public class WeakIfDiscardAction extends AbstractGameAction {
    private int weakApply;
    private AbstractMonster m;

    public WeakIfDiscardAction(AbstractMonster m, int amount) {
        setValues(AbstractDungeon.player, AbstractDungeon.player, 0);
        duration = Settings.ACTION_DUR_FAST;
        weakApply = amount;
        this.m = m;
    }

    public void update() {
        if (GameActionManager.totalDiscardedThisTurn > 0 && !m.isDeadOrEscaped())
            applyToEnemy(m, new WeakPower(m, weakApply, false));

        isDone = true;
    }
}
