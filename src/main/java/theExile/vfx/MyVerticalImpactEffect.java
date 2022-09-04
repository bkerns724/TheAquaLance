package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.GenericSmokeEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theExile.util.Wiz;

public class MyVerticalImpactEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float mx;
    private float my;
    private static final float DURATION = 0.6F;
    private TextureAtlas.AtlasRegion img;
    private boolean playedSound = false;

    public MyVerticalImpactEffect(float x, float y, float mx, float my) {
        img = ImageMaster.VERTICAL_IMPACT;
        this.x = x - (float)img.packedWidth / 2.0F;
        this.y = y - (float)img.packedHeight * 0.01F;
        this.mx = mx;
        this.my = my;
        startingDuration = DURATION;
        duration = DURATION;
        scale = Settings.scale;
        rotation = MathUtils.random(40.0F, 50.0F);
        color = Color.SCARLET.cpy();
        renderBehind = false;

        for(int i = 0; i < 50; ++i) {
            AbstractDungeon.effectsQueue.add(new GenericSmokeEffect(x + MathUtils.random(-280.0F, 250.0F) * Settings.scale,
                    y - 80.0F * Settings.scale));
        }

    }

    private void playRandomSfX() {
        CardCrawlGame.sound.playA("BLUNT_HEAVY", -0.3F);
    }

    public void update() {
        if (duration == startingDuration)
            Wiz.vfx(new FlashAtkImgEffect(mx, my, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F) {
            isDone = true;
        }

        if (duration < 0.5F && !playedSound) {
            playRandomSfX();
            playedSound = true;

        }

        if (duration > 0.2F) {
            color.a = Interpolation.fade.apply(0.5F, 0.0F, (duration - 0.34F) * 5.0F);
        } else {
            color.a = Interpolation.fade.apply(0.0F, 0.5F, duration * 5.0F);
        }

        scale = Interpolation.fade.apply(Settings.scale * 1.1F, Settings.scale * 1.05F, duration / 0.6F);
    }

    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.setBlendFunction(770, 1);
        sb.draw(img, x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, y, (float)img.packedWidth / 2.0F,
                0.0F, (float)img.packedWidth, (float)img.packedHeight, scale * 0.3F, scale * 0.8F,
                rotation - 18.0F);
        sb.draw(img, x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, y, (float)img.packedWidth / 2.0F,
                0.0F, (float)img.packedWidth, (float)img.packedHeight, scale * 0.3F, scale * 0.8F,
                rotation + MathUtils.random(12.0F, 18.0F));
        sb.draw(img, x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, y, (float)img.packedWidth / 2.0F,
                0.0F, (float)img.packedWidth, (float)img.packedHeight, scale * 0.4F, scale * 0.5F,
                rotation - MathUtils.random(-10.0F, 14.0F));
        sb.draw(img, x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, y, (float)img.packedWidth / 2.0F,
                0.0F, (float)img.packedWidth, (float)img.packedHeight, scale * 0.7F, scale * 0.9F,
                rotation + MathUtils.random(20.0F, 28.0F));
        sb.draw(img, x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, y, (float)img.packedWidth / 2.0F,
                0.0F, (float)img.packedWidth, (float)img.packedHeight, scale * 1.5F,
                scale * MathUtils.random(1.4F, 1.6F), rotation);
        Color c = Color.GOLD.cpy();
        c.a = color.a;
        sb.setColor(c);
        sb.draw(img, x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, y, (float)img.packedWidth / 2.0F,
                0.0F, (float)img.packedWidth, (float)img.packedHeight, scale,
                scale * MathUtils.random(0.8F, 1.2F), rotation);
        sb.draw(img, x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, y, (float)img.packedWidth / 2.0F,
                0.0F, (float)img.packedWidth, (float)img.packedHeight, scale,
                scale * MathUtils.random(0.4F, 0.6F), rotation);
        sb.draw(img, x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, y, (float)img.packedWidth / 2.0F,
                0.0F, (float)img.packedWidth, (float)img.packedHeight,
                scale * 0.5F, scale * 0.7F, rotation + MathUtils.random(20.0F, 28.0F));
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
