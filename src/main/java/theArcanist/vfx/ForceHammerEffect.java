package theArcanist.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageImpactCurvyEffect;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

import static theArcanist.util.Wiz.adp;

public class ForceHammerEffect extends AbstractGameEffect {
    public static final float EFFECT_DUR = 0.6F;
    public static final float DUR_BEFORE_IMPACT = 0.4F;
    public static final float DUR_DIFFERENCE = EFFECT_DUR - DUR_BEFORE_IMPACT;
    private float x;
    private float y;
    private float startY;
    private float targetY;
    private static TextureAtlas.AtlasRegion img;
    private boolean impactHook;

    public ForceHammerEffect(float x, float y) {
        impactHook = false;
        if (img == null) {
            Texture texture = TexLoader.getTexture(ArcanistMod.FORCE_L_EFFECT_FILE);
            img = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        }

        scale = Settings.scale;
        targetY = y - 160.0f;
        startY = (float)Settings.HEIGHT + (float)img.packedHeight / 2.0F;
        this.y = startY;
        this.x = x - (float)img.packedWidth * 0.8f;
        duration = EFFECT_DUR;
        color = Color.WHITE.cpy();
    }

    public ForceHammerEffect() {
        impactHook = false;
        if (img == null) {
            Texture texture = TexLoader.getTexture(ArcanistMod.FORCE_L_EFFECT_FILE);
            img = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        }

        scale = Settings.scale * 2.0f;
        targetY = adp().hb.y - 25.0f;
        startY = (float)Settings.HEIGHT + (float)img.packedHeight / 2.0F;
        y = startY;
        x = Settings.WIDTH * 0.7f - (float)img.packedWidth * 1.2f;
        duration = EFFECT_DUR;
        color = Color.WHITE.cpy();
    }

    public void update() {
        if (duration > (DUR_DIFFERENCE))
            y = Interpolation.linear.apply(startY, targetY,
                    1.0f - ((duration - DUR_DIFFERENCE) / DUR_BEFORE_IMPACT));
        else
            y = targetY;
        duration -= Gdx.graphics.getDeltaTime();
        if (duration <= DUR_DIFFERENCE) {
            if (!impactHook) {
                impactHook = true;
                CardCrawlGame.sound.playA("BLOCK_BREAK", -0.3F);
                AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.MAGENTA));
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED, true);

                int i;
                for(i = 0; i < 5; ++i)
                    AbstractDungeon.effectsQueue.add(new DamageImpactCurvyEffect(x + (float)img.packedWidth / 2.0F,
                            y + (float)img.packedWidth / 2.0F));

                for(i = 0; i < 30; ++i)
                    AbstractDungeon.effectsQueue.add(new UpgradeShineParticleEffect(
                            x + MathUtils.random(-100.0F, 100.0F) * Settings.scale + (float)img.packedWidth / 2.0F,
                            y + MathUtils.random(-50.0F, 120.0F) * Settings.scale + (float)img.packedHeight / 2.0F));
            }

            color.a = Interpolation.fade.apply(0.0F, 1.0F, duration/(DUR_DIFFERENCE));
        }
        if (duration < 0.0F)
            isDone = true;
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(color);
        sb.draw(img, x, y, (float)img.packedWidth * 0.5f, (float)img.packedHeight * 0.5f,
                (float)img.packedWidth, (float)img.packedHeight,
                scale, scale, 0.0f);
        sb.setBlendFunction(770, 771);
    }

    @Override
    public void dispose() { }
}
