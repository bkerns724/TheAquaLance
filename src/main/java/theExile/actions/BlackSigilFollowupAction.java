package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class BlackSigilFollowupAction extends AbstractGameAction {
    private int healAmount;

    public BlackSigilFollowupAction(AbstractMonster monster, int healAmount) {
        target = monster;
        this.healAmount = healAmount;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (target == null || target.isDying || target.currentHealth < 0)
            if (!target.hasPower(MinionPower.POWER_ID) && !target.halfDead)
                att(new HealAction(adp(), adp(), healAmount));
        isDone =true;
    }
}
