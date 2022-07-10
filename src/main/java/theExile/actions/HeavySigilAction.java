package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theExile.util.Wiz.applyToEnemyTop;
import static theExile.util.Wiz.att;

public class HeavySigilAction extends AbstractGameAction {
    private int strengthLoss;
    private DamageInfo info;
    private AttackEffect effect;

    public HeavySigilAction(AbstractMonster monster, DamageInfo info, int strengthLoss, AttackEffect effect) {
        target = monster;
        this.info = info;
        this.strengthLoss = strengthLoss;
        this.effect = effect;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (shouldCancelAction())
            isDone = true;
        else {
            tickDuration();
            if (isDone) {
                applyToEnemyTop(target, new StrengthPower(target, -strengthLoss));
                att(new AttackAction((AbstractMonster) target, info, effect, null, false));

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                    AbstractDungeon.actionManager.clearPostCombatActions();
                else
                    addToTop(new WaitAction(0.1F));
            }
        }
    }
}
