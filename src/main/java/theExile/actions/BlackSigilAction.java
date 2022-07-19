package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.util.Wiz.att;

public class BlackSigilAction extends AbstractGameAction {
    private final int healAmount;
    private final DamageInfo info;
    private final AttackEffect effect;

    public BlackSigilAction(AbstractMonster monster, DamageInfo info, int healAmount, AttackEffect effect) {
        target = monster;
        this.info = info;
        this.healAmount = healAmount;
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
                att(new BlackSigilFollowupAction((AbstractMonster) target, healAmount));
                att(new AttackAction((AbstractMonster) target, info, effect));
            }
        }
    }
}
