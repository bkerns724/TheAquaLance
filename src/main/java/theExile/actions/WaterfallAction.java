package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theExile.ExileMod;
import theExile.powers.FrostbitePower;

import static theExile.util.Wiz.*;

public class WaterfallAction extends AbstractGameAction {
    private DamageInfo info;

    public WaterfallAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        setValues(target, info);
        actionType = ActionType.DAMAGE;
        startDuration = Settings.ACTION_DUR_FAST;
        duration = startDuration;
    }

    public void update() {
        if (shouldCancelAction())
            isDone = true;
        else {
            tickDuration();
            if (isDone) {
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, ExileMod.Enums.WATER));
                target.damage(info);
                if (target.lastDamageTaken > 0)
                    applyToEnemyTop(target, new FrostbitePower(target, target.lastDamageTaken));

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                    AbstractDungeon.actionManager.clearPostCombatActions();
                else {
                    vfxTop(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, ExileMod.Enums.ICE));
                    addToTop(new WaitAction(0.1F));
                }
            }
        }
    }
}
