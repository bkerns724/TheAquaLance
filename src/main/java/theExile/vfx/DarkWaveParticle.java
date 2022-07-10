package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class DarkWaveParticle extends AbstractGameEffect {
    private static final float EFFECT_DUR = 0.5F;
    private float x;
    private float y;
    private float targetY;
    private static AtlasRegion img;
    private boolean impactHook = false;
    private float extraScale;

    public DarkWaveParticle(float x, float y, float extraScale) {
        this.extraScale = extraScale;
        if (img == null)
            img = ImageMaster.vfxAtlas.findRegion("combat/weightyImpact");

        this.scale = Settings.scale;
        this.x = x - (float)img.packedWidth / 2.0F;
        this.y = (float)Settings.HEIGHT - (float)img.packedHeight / 2.0F;
        duration = EFFECT_DUR;
        targetY = y - 180.0F * Settings.scale;
        rotation = 0.0F;
        color = new Color(0.5F, 0F, 0.5F, 0.0F);
    }

    public void update() {
        y = Interpolation.fade.apply((float)Settings.HEIGHT, targetY, 1.0F - duration / EFFECT_DUR);
        scale += Gdx.graphics.getDeltaTime();
        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F) {
            isDone = true;
        } else if (duration < 0.2F) {
            if (!impactHook) {
                impactHook = true;
                CardCrawlGame.screenShake.shake(ShakeIntensity.LOW, ShakeDur.SHORT, false);
            }

            color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, duration * 5.0F);
        } else
            color.a = Interpolation.fade.apply(1.0F, 0.0F, duration / EFFECT_DUR);
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        color.r = 0.5f;
        color.b = 0.5f;
        sb.setColor(color);
        sb.draw(img, x, y + 140.0F * Settings.scale, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth / 2.0F, (float)img.packedHeight * (duration + 0.2F) * 3.0F,
                scale * MathUtils.random(0.99F, 1.01F) * 0.5F * extraScale,
                scale * MathUtils.random(0.99F, 1.01F) * 2.0F * extraScale * (duration + 0.8F), rotation);
        color.r = 0.35f;
        color.b = 0.35f;
        sb.setColor(color);
        sb.draw(img, x - 50.0F * Settings.scale, y + 140.0F * Settings.scale, (float)img.packedWidth / 2.0F,
                (float)img.packedHeight / 2.0F, (float)img.packedWidth / 2.0F,
                (float)img.packedHeight * (duration + 0.2F) * 2.0F,
                extraScale * scale * MathUtils.random(0.99F, 1.01F) * 0.6F,
                extraScale * scale * MathUtils.random(0.99F, 1.01F) * 2.0F * (duration + 0.8F), rotation);
        color.r = 0.25f;
        color.b = 0.25f;
        sb.setColor(color);
        sb.draw(img, x - 100.0F * Settings.scale, y + 140.0F * Settings.scale, (float)img.packedWidth / 2.0F,
                (float)img.packedHeight / 2.0F, (float)img.packedWidth,
                (float)img.packedHeight * (duration + 0.2F) * 1.0F,
                extraScale * scale * MathUtils.random(0.99F, 1.01F) * 0.75F,
                extraScale * scale * MathUtils.random(0.99F, 1.01F) * 2.0F * (duration + 0.8F), rotation);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
