package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SoulFireballEffect extends AbstractGameEffect {
    private static final float FIREBALL_INTERVAL = 0.016F;
    private float x;
    private float y;
    private float startX;
    private float startY;
    private float targetX;
    private float targetY;
    private float vfxTimer = 0.0F;
    private int effectSize;

    public SoulFireballEffect(float startX, float startY, float targetX, float targetY, int effectSize) {
        this.startingDuration = 0.3F;
        this.duration = 0.3F;
        this.startX = startX;
        this.startY = startY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.effectSize = effectSize;
        this.x = startX;
        this.y = startY;
    }

    public void update() {
        x = Interpolation.fade.apply(targetX, startX, duration / startingDuration);
        if (duration > startingDuration / 2.0F) {
            y = Interpolation.pow4In.apply(startY, targetY, (duration - startingDuration / 2.0F) / startingDuration * 2.0F);
        } else {
            y = Interpolation.pow4Out.apply(targetY, startY, duration / startingDuration * 2.0F);
        }

        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0F) {
            vfxTimer += 0.016F;
            AbstractDungeon.effectsQueue.add(new SoulFireBurstParticleEffect(x, y, effectSize));
            AbstractDungeon.effectsQueue.add(new SoulFireBurstParticleEffect(x, y, effectSize));
        }

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F) {
            isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}