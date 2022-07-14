package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theExile.ExileMod;

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
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, ExileMod.Enums.FORCE, false));
                att(new BlackSigilFollowupAction((AbstractMonster) target, healAmount));
                att(new AttackAction((AbstractMonster) target, info, effect));
            }
        }
    }
}
