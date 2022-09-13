package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theExile.util.Wiz.atb;

public class HeatRayAction extends AbstractGameAction {
    private final DamageInfo info;

    public HeatRayAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        this.target = target;
        actionType = ActionType.DAMAGE;
        duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FASTER && target != null) {
            target.damage(info);
            if (target.isDying || target.currentHealth <= 0) {
                addToBot(new GainEnergyAction(1));
                atb(new DrawCardAction(1));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }

        tickDuration();
    }
}
