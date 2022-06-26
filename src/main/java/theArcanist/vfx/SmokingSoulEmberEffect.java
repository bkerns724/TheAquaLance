package theArcanist.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.FastSmokeParticle;

public class SmokingSoulEmberEffect extends AbstractGameEffect {
    private float x;
    private float vX;
    private float y;
    private float vY;
    private float gravity;
    private static final float INTERVAL = 0.01F;
    private float smokeTimer = 0.0F;

    public SmokingSoulEmberEffect(float x, float y) {
        this.x = x;
        this.y = y;
        vX = MathUtils.random(-600.0F, 600.0F) * Settings.scale;
        vY = MathUtils.random(-200.0F, 600.0F) * Settings.scale;
        gravity = 800.0F * Settings.scale;
        scale = MathUtils.random(0.2F, 0.4F) * Settings.scale;
        duration = MathUtils.random(0.3F, 0.6F);
    }

    public void update() {
        x += vX * Gdx.graphics.getDeltaTime();
        y += vY * Gdx.graphics.getDeltaTime();
        vY -= gravity * Gdx.graphics.getDeltaTime();
        smokeTimer -= Gdx.graphics.getDeltaTime();
        if (smokeTimer < 0.0F) {
            smokeTimer = INTERVAL;
            AbstractDungeon.effectsQueue.add(new FastSoulSmokeParticle(x, y));
        }

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F)
            isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
