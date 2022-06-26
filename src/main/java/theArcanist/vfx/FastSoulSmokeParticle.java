package theArcanist.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FastSoulSmokeParticle extends AbstractGameEffect {
    private float x;
    private float y;
    //private float vX;
    private float scale = 0.01F;
    private float targetScale;
    private static TextureAtlas.AtlasRegion img;

    public FastSoulSmokeParticle(float x, float y) {
        if (img == null)
            img = ImageMaster.EXHAUST_L;

        targetScale = MathUtils.random(0.3F, 0.6F) * Settings.scale;
        color = new Color(MathUtils.random(0.5F, 0.8F), MathUtils.random(0.15F, 0.4F), MathUtils.random(0.5F, 0.9F), 0.8F);
        this.x = x - (float)img.packedWidth / 2.0F;
        this.y = y - (float)img.packedHeight / 2.0F;
        rotation = MathUtils.random(360.0F);
        duration = 0.6F;
    }

    public void update() {
        if (color.g > 0.1F)
            color.g -= Gdx.graphics.getDeltaTime() * 3.0F;
        if (color.b > 0.05F)
            color.b -= Gdx.graphics.getDeltaTime() * 1.5f;
        if (color.r > 0.1F)
            color.r -= Gdx.graphics.getDeltaTime() * 3.0F;

        scale = Interpolation.swingIn.apply(targetScale, 0.1F, duration / 0.6F);
        //rotation += vX * 2.0F * Gdx.graphics.getDeltaTime();
        color.a = duration;
        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F)
            isDone = true;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.draw(img, x, y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth, (float)img.packedHeight, scale, scale, rotation);
    }

    public void dispose() {
    }
}
