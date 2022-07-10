package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SoulFireBurstParticleEffect extends AbstractGameEffect {
    private AtlasRegion img;
    private static final float DUR = 1.0F;
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float floor;
    private static final float GRAVITY;

    public SoulFireBurstParticleEffect(float x, float y, int effectSize) {
        int roll = MathUtils.random(0, 2);
        if (roll == 0)
            img = ImageMaster.FLAME_1;
        else if (roll == 1)
            img = ImageMaster.FLAME_2;
        else
            img = ImageMaster.FLAME_3;

        duration = MathUtils.random(0.5F, 1.0F);
        this.x = x - (float)(img.packedWidth / 2);
        this.y = y - (float)(img.packedHeight / 2) + MathUtils.random(-20.0F, 20.0F) * Settings.scale;
        color = new Color(MathUtils.random(0.4F, 0.8F), MathUtils.random(0.1F, 0.4F), MathUtils.random(0.4F, 0.9F), 0.8F);
        color.a = 0.0F;
        rotation = MathUtils.random(-10.0F, 10.0F);
        scale = MathUtils.random(1.5F, 2.0F);
        scale += (float)effectSize * 0.1F;
        scale *= Settings.scale;
        vX = MathUtils.random(-900.0F, 900.0F) * Settings.scale;
        vY = MathUtils.random(0.0F, 300.0F) * Settings.scale;
        floor = MathUtils.random(100.0F, 250.0F) * Settings.scale;
    }

    public void update() {
        vY += GRAVITY / scale * Gdx.graphics.getDeltaTime();
        x += vX * Gdx.graphics.getDeltaTime() * MathUtils.sinDeg(Gdx.graphics.getDeltaTime());
        y += vY * Gdx.graphics.getDeltaTime();
        if (scale > 0.3F * Settings.scale)
            scale -= Gdx.graphics.getDeltaTime() * 2.0F;

        if (y < floor) {
            vY = -vY * 0.75F;
            y = floor + 0.1F;
            vX *= 1.1F;
        }

        if (DUR - duration < 0.1F)
            color.a = Interpolation.fade.apply(0.0F, 1.0F, (DUR - duration) * 10.0F);
        else
            color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, duration);

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F)
            isDone = true;
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(color);
        sb.draw(img, x, y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth, (float)img.packedHeight, scale, scale, rotation);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }

    static {
        GRAVITY = 180.0F * Settings.scale;
    }
}