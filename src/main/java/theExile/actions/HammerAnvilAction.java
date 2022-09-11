package theExile.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theExile.powers.CrushedPower;
import theExile.util.Wiz;

public class HammerAnvilAction extends AbstractGameAction {
    private final DamageInfo info;
    private final int threshold;

    public HammerAnvilAction(AbstractCreature target, int threshold, DamageInfo info) {
        this.info = info;
        this.threshold = threshold;
        setValues(target, info);
        actionType = ActionType.DAMAGE;
        startDuration = Settings.ACTION_DUR_FAST;
        duration = startDuration;
    }

    public void update() {
        isDone = true;
        if (shouldCancelAction())
            return;

        target.damage(info);
        int crushed = target.lastDamageTaken / threshold;
        if (crushed > 0) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AttackEffect.BLUNT_HEAVY));
            Wiz.applyToEnemyTop(target, new CrushedPower(target, crushed));
        }

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
            AbstractDungeon.actionManager.clearPostCombatActions();
        else
            addToTop(new WaitAction(0.1F));
    }
}

