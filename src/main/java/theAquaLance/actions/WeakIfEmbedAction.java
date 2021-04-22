package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.util.Wiz.*;

public class WeakIfEmbedAction extends AbstractGameAction {
    private int weakApply;
    private AbstractMonster m;

    public WeakIfEmbedAction(AbstractMonster m, int amount) {
        setValues(AbstractDungeon.player, AbstractDungeon.player, 0);
        duration = Settings.ACTION_DUR_FAST;
        weakApply = amount;
        this.m = m;
    }

    public void update() {
        if (!m.isDeadOrEscaped() && m.hasPower(EmbedPower.POWER_ID))
            applyToEnemy(m, new WeakPower(m, weakApply, false));

        isDone = true;
    }
}
