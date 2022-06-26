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

// Code "Borrowed" from AlisonMoons
public class ArcanistVictoryEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vX;
    private float vY;
    private boolean flipX = MathUtils.randomBoolean();
    private TextureAtlas.AtlasRegion img;

    public ArcanistVictoryEffect() {
        duration = 1.0F;
        startingDuration = duration;
        renderBehind = MathUtils.randomBoolean();
        switch(MathUtils.random(2)) {
            case 0:
                img = ImageMaster.FLAME_1;
                break;
            case 1:
                img = ImageMaster.FLAME_2;
                break;
            default:
                img = ImageMaster.FLAME_3;
        }

        x = MathUtils.random(600.0F, 1320.0F) * Settings.xScale - (float)img.packedWidth / 2.0F;
        y = -300.0F * Settings.scale - (float)img.packedHeight / 2.0F;
        if (x > 960.0F * Settings.xScale)
            vX = MathUtils.random(0.0F, -120.0F) * Settings.xScale;
        else
            vX = MathUtils.random(120.0F, 0.0F) * Settings.xScale;

        vY = MathUtils.random(600.0F, 800.0F) * Settings.scale;
        // color = new Color(MathUtils.random(0.4F, 0.8F), MathUtils.random(0.1F, 0.4F), MathUtils.random(0.4F, 0.9F), 0.8F);
        color = Color.ORANGE.cpy();
        renderBehind = false;
        scale = MathUtils.random(6.0F, 7.0F) * Settings.scale;
    }

    public void update() {
        x += vX * Gdx.graphics.getDeltaTime();
        y += vY * Gdx.graphics.getDeltaTime();
        color.a = Interpolation.pow3Out.apply(0.0F, 0.8F, duration / startingDuration);
        duration -= Gdx.graphics.getDeltaTime();
        scale += Gdx.graphics.getDeltaTime();
        if (duration < 0.0F)
            isDone = true;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(color);
        if (flipX && !img.isFlipX())
            img.flip(true, false);
        else if (!flipX && img.isFlipX())
            img.flip(true, false);

        sb.draw(img, x, y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, scale, scale, rotation);
    }

    public void dispose() {
    }
}