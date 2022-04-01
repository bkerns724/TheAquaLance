package theArcanist.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import com.megacrit.cardcrawl.vfx.combat.IronWaveParticle;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import theArcanist.ArcanistMod;
import theArcanist.potions.PoisonousSmokeBomb;

import java.lang.reflect.Method;

import static theArcanist.util.Wiz.*;

public class PoisonBombAction extends AbstractGameAction {
    public boolean startedDying = false;
    public boolean startedClear = false;
    private static final float DURATION = 2.6f;

    public PoisonBombAction() {
        duration = DURATION;
        startDuration = DURATION;
    }

    @Override
    public void update() {
        if (startDuration == duration) {
            for (AbstractMonster m : getEnemies())
                AbstractDungeon.effectsQueue.add(new SmokeBombEffect(m.hb.cX, m.hb.cY));
            adp().hideHealthBar();
            adp().isEscaping = true;
            adp().flipHorizontal = !AbstractDungeon.player.flipHorizontal;
            adp().escapeTimer = 2.5f;
            AbstractDungeon.overlayMenu.endTurnButton.disable();
        }

        if (startDuration - duration >= 0.25f && !startedDying) {
            startedDying = true;
            for (AbstractMonster m : getEnemies()) {
                m.isDying = true;
                m.deathTimer = 1.0f;
                m.hideHealthBar();
                try {
                    Class<? extends AbstractMonster> monClass = m.getClass();
                    Method method = ReflectionHacks.getCachedMethod(monClass, "playDeathSfx");
                    method.invoke(m);
                }
                catch (Exception e) {}
            }
        }

        if (startDuration - duration >= 0.75 && !startedClear) {
            startedClear = true;
            for (AbstractCard c : AbstractDungeon.player.limbo.group)
                AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
            AbstractDungeon.player.limbo.clear();
        }

        tickDuration();
    }
}
