package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ImpactSparkEffect;

public class GreenLightningEffect extends AbstractGameEffect {
    private final float x;
    private final float y;
    private final boolean screenshake;
    private final boolean sound;
    private static TextureAtlas.AtlasRegion img = null;
    private static final float DURATION = 0.5f;

    public GreenLightningEffect(float x, float y) {
        this(x, y, true, true);
    }

    public GreenLightningEffect(float x, float y, boolean shake) {
        this(x, y, shake, true);
    }

    public GreenLightningEffect(float x, float y, boolean screenshake, boolean sound) {
        if (img == null)
            img = ImageMaster.vfxAtlas.findRegion("combat/lightning");

        this.x = x - (float)img.packedWidth / 2.0F;
        this.y = y;
        this.screenshake = screenshake;
        this.sound = sound;
        color = Color.LIME.cpy();
        duration = startingDuration = DURATION;
    }

    public void update() {
        if (duration == startingDuration) {
            if (screenshake) {
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.MED, false);
                if (sound)
                    CardCrawlGame.sound.play("ORB_LIGHTNING_PASSIVE");
            }
            else if (sound)
                CardCrawlGame.sound.play("ORB_LIGHTNING_EVOKE");

            for(int i = 0; i < 15; ++i) {
                AbstractDungeon.topLevelEffectsQueue.add(
                        new ImpactSparkEffect(x + MathUtils.random(-20.0F, 20.0F) * Settings.scale + 150.0F * Settings.scale,
                                y + MathUtils.random(-20.0F, 20.0F) * Settings.scale));
            }
        }

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F)
            isDone = true;

        color.a = Interpolation.bounceIn.apply(duration * 2.0F);
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(color);
        sb.draw(img, x, y, (float)img.packedWidth / 2.0F, 0.0F, (float)img.packedWidth,
                (float)img.packedHeight, scale, scale, rotation);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
