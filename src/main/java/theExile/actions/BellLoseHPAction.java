package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theExile.ExileMod;

import static theExile.util.Wiz.*;

public class BellLoseHPAction extends AbstractGameAction {
    private static final float DURATION = 0.25F;

    public BellLoseHPAction(AbstractCreature target, int amount) {
        this.setValues(target, adp(), amount);
        actionType = ActionType.DAMAGE;
        duration = startDuration = DURATION;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT)
            isDone = true;
        else {
            tickDuration();
            if (isDone) {
                if (target.currentHealth > 0) {
                    target.damage(new DamageInfo(source, amount, DamageInfo.DamageType.HP_LOSS));
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, ExileMod.Enums.BELL));
                }

                if (adRoom().monsters.areMonstersBasicallyDead())
                    AbstractDungeon.actionManager.clearPostCombatActions();

                att(new WaitAction(0.1F));
            }
        }
    }
}
