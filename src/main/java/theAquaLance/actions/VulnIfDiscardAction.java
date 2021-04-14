package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static theAquaLance.util.Wiz.*;

public class VulnIfDiscardAction extends AbstractGameAction {
    private int vulnApply;
    private AbstractMonster m;

    public VulnIfDiscardAction(AbstractMonster m, int amount) {
        setValues(AbstractDungeon.player, AbstractDungeon.player, 0);
        duration = Settings.ACTION_DUR_FAST;
        vulnApply = amount;
        this.m = m;
    }

    public void update() {
        if (GameActionManager.totalDiscardedThisTurn > 0 && !m.isDeadOrEscaped())
            applyToEnemy(m, new VulnerablePower(m, vulnApply, false));

        isDone = true;
    }
}
