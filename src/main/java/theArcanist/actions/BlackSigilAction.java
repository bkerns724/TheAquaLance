package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.applyToSelfTop;

public class BlackSigilAction extends AbstractGameAction {
    private int strengthGain;
    private DamageInfo info;

    public BlackSigilAction(AbstractMonster monster, DamageInfo info, int strengthGain) {
        target = monster;
        this.info = info;
        this.strengthGain = strengthGain;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (shouldCancelAction())
            isDone = true;
        else {
            tickDuration();
            if (isDone) {
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, ArcanistMod.Enums.FORCE, false));
                target.damage(info);
                if (((target).isDying || target.currentHealth <= 0) && !target.halfDead)
                    applyToSelfTop(new StrengthPower(adp(), strengthGain));

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                } else {
                    addToTop(new WaitAction(0.1F));
                }
            }
        }
    }
}
