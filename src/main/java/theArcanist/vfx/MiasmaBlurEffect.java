package theArcanist.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class MiasmaBlurEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vY;
    private float aV;
    private float startDur;
    private float targetScale;
    private AtlasRegion img;

    public MiasmaBlurEffect(float x, float y) {
        color = new Color(0.0F, 0.0F, 0.0F, 1.0F);
        color.r = MathUtils.random(0.5F, 0.6F);
        color.g = MathUtils.random(0.0F, 0.2F);
        color.b = MathUtils.random(0.5F, 0.6F);
        if (MathUtils.randomBoolean()) {
            img = ImageMaster.EXHAUST_L;
            duration = MathUtils.random(2.0F, 2.5F);
            targetScale = MathUtils.random(0.8F, 2.2F);
        } else {
            img = ImageMaster.EXHAUST_S;
            duration = MathUtils.random(2.0F, 2.5F);
            targetScale = MathUtils.random(0.8F, 1.2F);
        }

        startDur = duration;
        this.x = x + MathUtils.random(-180.0F * Settings.scale, 150.0F * Settings.scale) - (float)img.packedWidth / 2.0F;
        this.y = y + MathUtils.random(-240.0F * Settings.scale, 150.0F * Settings.scale) - (float)img.packedHeight / 2.0F;
        scale = 0.01F;
        rotation = MathUtils.random(360.0F);
        aV = MathUtils.random(-250.0F, 250.0F);
        vY = MathUtils.random(Settings.scale, 5.0F * Settings.scale);
    }

    public void update() {
        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F) {
            isDone = true;
        }

        x += MathUtils.random(-2.0F * Settings.scale, 2.0F * Settings.scale);
        x += vY;
        y += MathUtils.random(-2.0F * Settings.scale, 2.0F * Settings.scale);
        y += vY;
        rotation += aV * Gdx.graphics.getDeltaTime();
        scale = Interpolation.exp10Out.apply(0.01F, targetScale, 1.0F - duration / startDur);
        if (duration < 0.33F) {
            color.a = duration * 3.0F;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.draw(img, x, y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth, (float)img.packedHeight, scale, scale, rotation);
    }

    public void dispose() {
    }
}
