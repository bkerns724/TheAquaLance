package theArcanist.vfx;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageImpactCurvyEffect;

public class MyWeightyImpactEffect extends AbstractGameEffect {
    private static final float EFFECT_DUR = 1.0F;
    private float x;
    private float y;
    private float targetY;
    private static AtlasRegion img;
    private boolean impactHook;
    private boolean soundHook;

    public MyWeightyImpactEffect(float x, float y) {
        this(x, y, new Color(1.0F, 1.0F, 0.1F, 0.0F));
    }

    public MyWeightyImpactEffect(float x, float y, Color newColor) {
        impactHook = false;
        soundHook = false;
        if (img == null)
            img = ImageMaster.vfxAtlas.findRegion("combat/weightyImpact");

        scale = Settings.scale;
        this.x = x - (float)img.packedWidth / 2.0F;
        this.y = (float)Settings.HEIGHT - (float)img.packedHeight / 2.0F;
        duration = EFFECT_DUR;
        targetY = y - 180.0F * Settings.scale;
        rotation = MathUtils.random(-1.0F, 1.0F);
        color = newColor;
    }

    public void update() {
        y = Interpolation.fade.apply((float)Settings.HEIGHT, targetY, 1.0F - duration / EFFECT_DUR);
        scale += Gdx.graphics.getDeltaTime();
        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.3f && !soundHook) {
            soundHook = true;
            for (int i = 0; i < 5; i++)
                CardCrawlGame.sound.playA("BLUNT_HEAVY", -0.3F);
        }
        if (duration < 0.0F)
            isDone = true;
        else if (duration < 0.2F) {
            if (!impactHook) {
                impactHook = true;
                AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.ORANGE));
                CardCrawlGame.screenShake.shake(ShakeIntensity.HIGH, ShakeDur.MED, true);

                int i;
                for(i = 0; i < 5; ++i)
                    AbstractDungeon.effectsQueue.add(new DamageImpactCurvyEffect(x + (float)img.packedWidth / 2.0F, y + (float)img.packedWidth / 2.0F));
                for(i = 0; i < 30; ++i)
                    AbstractDungeon.effectsQueue.add(new UpgradeShineParticleEffect(x + MathUtils.random(-100.0F, 100.0F) * Settings.scale + (float)img.packedWidth / 2.0F, y + MathUtils.random(-50.0F, 120.0F) * Settings.scale + (float)img.packedHeight / 2.0F));
            }
            color.a = Interpolation.fade.apply(0.0F, 0.5F, 0.2F / duration);
        } else
            color.a = Interpolation.pow2Out.apply(0.6F, 0.0F, duration / EFFECT_DUR);
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        color.g = 1.0F;
        sb.setColor(color);
        sb.draw(img, x, y + 140.0F * Settings.scale, (float)img.packedWidth / 2.0F,
                (float)img.packedHeight / 2.0F, (float)img.packedWidth,
                (float)img.packedHeight * (duration + 0.2F) * 5.0F,
                scale * MathUtils.random(0.99F, 1.01F) * 0.3F,
                scale * MathUtils.random(0.99F, 1.01F) * 2.0F * (duration + 0.8F), rotation);
        color.g = 0.6F;
        sb.setColor(color);
        sb.draw(img, x, y + 40.0F * Settings.scale, (float)img.packedWidth / 2.0F,
                (float)img.packedHeight / 2.0F, (float)img.packedWidth,
                (float)img.packedHeight * (duration + 0.2F) * 5.0F,
                scale * MathUtils.random(0.99F, 1.01F) * 0.7F,
                scale * MathUtils.random(0.99F, 1.01F) * 1.3F * (duration + 0.8F), rotation);
        color.g = 0.2F;
        sb.setColor(color);
        sb.draw(img, x, y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth, (float)img.packedHeight * (duration + 0.2F) * 5.0F,
                scale * MathUtils.random(0.99F, 1.01F),
                scale * MathUtils.random(0.99F, 1.01F) * (duration + 0.8F), rotation);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}