package theExile.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class ThornsDamageAction extends AbstractGameAction {
    private DamageInfo info;
    private static final float DURATION = 0.1F;

    public ThornsDamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect) {
        this.info = info;
        setValues(target, info);
        actionType = ActionType.DAMAGE;
        attackEffect = effect;
        this.duration = DURATION;
    }

    public void update() {
        if (shouldCancelAction())
            isDone = true;
        else {
            if (duration == DURATION) {
                if ((info.owner.isDying || info.owner.halfDead)) {
                    isDone = true;
                    return;
                }

                AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, attackEffect, false));
            }

            tickDuration();
            if (isDone) {
                if (attackEffect == AttackEffect.POISON) {
                    target.tint.color.set(Color.CHARTREUSE.cpy());
                    target.tint.changeColor(Color.WHITE.cpy());
                } else if (attackEffect == AttackEffect.FIRE) {
                    target.tint.color.set(Color.RED);
                    target.tint.changeColor(Color.WHITE.cpy());
                }

                target.damage(info);
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                    AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
    }
}