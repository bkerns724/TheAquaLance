package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theExile.util.Wiz;

import static theExile.util.Wiz.adp;

public class FastLoseHPAction extends AbstractGameAction {
    private final AbstractMonster target;
    private final int loss;
    private static final float DURATION = 0.1f;

    public FastLoseHPAction(AbstractMonster target, int loss) {
        this.target = target;
        this.loss = loss;
        actionType = ActionType.DAMAGE;
        duration = DURATION;
    }

    @Override
    public void update() {
        if (duration == DURATION && target.currentHealth > 0)
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, Wiz.getRandomSlash()));

        tickDuration();
        if (isDone) {
            target.damage(new DamageInfo(adp(), loss, DamageInfo.DamageType.HP_LOSS));
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }
    }
}
