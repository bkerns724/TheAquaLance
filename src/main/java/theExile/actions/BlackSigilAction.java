package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static theExile.util.Wiz.adp;

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
        if (duration == startDuration && target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, effect));
            target.damage(info);
            if ((target.isDying || target.currentHealth <= 0) && !target.halfDead && !target.hasPower(MinionPower.POWER_ID))
                adp().heal(healAmount, true);

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }

        tickDuration();
    }
}
