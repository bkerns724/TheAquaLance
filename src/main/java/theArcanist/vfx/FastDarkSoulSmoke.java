package theArcanist.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class FastDarkSoulSmoke {
    private float x;
    private float y;
    private float vX;
    private float rotation;
    private float fadeInTime;
    private float fadeInTimer;
    private float scale = 0.01F;
    private float targetScale;
    private boolean fadingIn = true;
    private Color color;
    private TextureAtlas.AtlasRegion img;
    private boolean killed = false;
    private float killSpeed;

    public FastDarkSoulSmoke(float x, float y) {
        targetScale = MathUtils.random(0.5F, 2.0F) * Settings.scale;
        fadeInTime = MathUtils.random(1.0F, 1.5F);
        fadeInTimer = fadeInTime;
        float darkness = MathUtils.random(0.0F, 0.1F);
        color = new Color(darkness + 0.15F, darkness + 0.05F, darkness + 0.15F, 1.0F);
        if (targetScale > 0.5F)
            img = ImageMaster.EXHAUST_L;
        else {
            img = ImageMaster.EXHAUST_S;
            vX /= 3.0F;
        }

        this.x = x + MathUtils.random(-100.0F, 100.0F) * Settings.scale - (float)img.packedWidth / 2.0F;
        this.y = y + MathUtils.random(-75.0F, 75.0F) * Settings.scale - (float)img.packedHeight / 2.0F;
        rotation = MathUtils.random(360.0F);
        killSpeed = MathUtils.random(1.0F, 4.0F);
    }

    public void update() {
        if (fadingIn) {
            fadeInTimer -= Gdx.graphics.getDeltaTime();
            if (fadeInTimer < 0.0F) {
                fadeInTimer = 0.0F;
                fadingIn = false;
            }

            scale = Interpolation.swingIn.apply(targetScale, 0.01F, fadeInTimer / fadeInTime);
        }

        x += vX * Gdx.graphics.getDeltaTime();
        rotation += vX * 2.0F * Gdx.graphics.getDeltaTime();
        if (killed) {
            color.a -= killSpeed * Gdx.graphics.getDeltaTime();

            scale += 5.0F * Gdx.graphics.getDeltaTime();
        }

    }

    public void kill() {
        killed = true;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.draw(img, x, y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth, (float)img.packedHeight, scale, scale, rotation);
    }
}
