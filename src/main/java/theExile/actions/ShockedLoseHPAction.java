package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class ShockedLoseHPAction extends AbstractGameAction {

    public ShockedLoseHPAction(AbstractCreature target, AbstractCreature source, int amount) {
        setValues(target, source, amount);
        actionType = ActionType.DAMAGE;
        duration = startDuration = 0.25f;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT)
            isDone = true;
        else {
            tickDuration();
            if (isDone) {
                if (target.currentHealth > 0)
                    target.damage(new DamageInfo(source, amount, DamageInfo.DamageType.HP_LOSS));

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                    AbstractDungeon.actionManager.clearPostCombatActions();

                addToTop(new WaitAction(0.1F));
            }
        }
    }
}
