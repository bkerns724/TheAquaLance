package theExile.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theExile.ExileMod;
import theExile.util.Wiz;

public class AcidSplashAction extends AbstractGameAction {
    private float x;
    private float y;
    private boolean allEnemy;
    private static final float DURATION = 0.075f;

    public AcidSplashAction(float x, float y) {
        this.x = x;
        this.y = y;
        allEnemy = false;
        duration = DURATION;
    }

    public AcidSplashAction(boolean allEnemy) {
        this.allEnemy = true;
        duration = DURATION;
    }

    @Override
    public void update() {
        if (duration == DURATION) {
            if (allEnemy)
                Wiz.forAllMonstersLiving(mon ->
                        Wiz.vfxTop(new FlashAtkImgEffect(mon.hb.cX + Settings.scale * MathUtils.random(-100f, 100f),
                                mon.hb.cY + Settings.scale * MathUtils.random(-100f, 100f), ExileMod.Enums.ACID_M)));
            else
                Wiz.vfxTop(new FlashAtkImgEffect(x + Settings.scale * MathUtils.random(-100f, 100f),
                        y + Settings.scale * MathUtils.random(-100f, 100f), ExileMod.Enums.ACID_M));
        }

        tickDuration();
    }
}
