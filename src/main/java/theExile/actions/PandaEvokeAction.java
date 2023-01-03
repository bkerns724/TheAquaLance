package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theExile.ExileMod;
import theExile.orbs.CrazyPanda;
import theExile.vfx.PandaExitEffect;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class PandaEvokeAction extends AbstractGameAction {
    private static final float DURATION = 1.0F;
    private float thunkTiming;
    private boolean thunkEffect;
    public CrazyPanda panda;
    private float targetX;
    private float targetY;
    private float sourceX;
    private float sourceY;
    public CrazyPanda original;

    public PandaEvokeAction(CrazyPanda panda, CrazyPanda original) {
        this.original = original;
        this.panda = panda;
        amount = panda.passiveAmount;
        actionType = ActionType.DAMAGE;
        duration = DURATION;
        thunkEffect = false;
    }

    public void update() {
        if (duration == DURATION) {
            target = AbstractDungeon.getRandomMonster();
            if (target == null) {
                isDone = true;
                return;
            } else
                amount = CrazyPanda.applyLockOn(target, amount);
            targetX = target.hb.cX;
            targetY = target.hb.cY;
            sourceX = original.cX;
            sourceY = original.cY;
            if (!ExileMod.pandaList.contains(panda))
                ExileMod.pandaList.add(panda);
            thunkTiming = (targetX - sourceX)/3200.0f;
            if (thunkTiming > 0.25f)
                thunkTiming = 0.25f;
            if (thunkTiming < 0.1f)
                thunkTiming = 0.1f;

            panda.startShoot();
            targetX = target.hb.cX + AbstractDungeon.miscRng.random(-25.0f*Settings.xScale, 25.0f*Settings.xScale);
            targetY = target.hb.cY + AbstractDungeon.miscRng.random(-25.0f*Settings.yScale, 25.0f*Settings.yScale);
            sourceX = panda.cX;
            sourceY = panda.cY;
        }

        panda.cX = sourceX + (targetX - sourceX)*(DURATION - duration)/ thunkTiming;
        panda.cY = sourceY + (targetY - sourceY)*(DURATION - duration)/ thunkTiming;

        if (duration <= DURATION - thunkTiming && !thunkEffect) {
            thunkEffect = true;
            panda.cX = targetX;
            panda.cY = targetY;
            if (target != null && target.currentHealth > 0 && adp() != null) {
                DamageInfo info = new DamageInfo(adp(), panda.passiveAmount, DamageInfo.DamageType.THORNS);
                att(new DamageAction(target, info, AttackEffect.BLUNT_HEAVY, true));
            }
            AbstractDungeon.effectList.add(new PandaExitEffect(panda));
            isDone = true;
        }

        tickDuration();
    }
}
