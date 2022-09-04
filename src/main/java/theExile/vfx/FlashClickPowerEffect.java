package theExile.vfx;

import basemod.ClickableUIElement;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theExile.ExileMod;
import theExile.util.ClickableForPower;

public class FlashClickPowerEffect extends AbstractGameEffect {
    private final Texture img;
    private final TextureAtlas.AtlasRegion region;
    private float scale;
    private static final float DURATION = 1.5F;
    private final ClickableForPower clicky;

    public FlashClickPowerEffect(ClickableForPower clicky) {
        this.clicky = clicky;
        img = ReflectionHacks.getPrivate(clicky, ClickableUIElement.class, "image");
        region = ReflectionHacks.getPrivate(clicky, ClickableUIElement.class, "region");
        startingDuration = DURATION;
        duration = DURATION;
        renderBehind = false;
        scale = Settings.scale;
        color = new Color(1.0F, 1.0F, 1.0F, 0.5F);
    }

    public void update() {
        duration -= Gdx.graphics.getDeltaTime();
        scale = Interpolation.exp5Out.apply(3.0F * Settings.scale, Settings.scale, (startingDuration - duration) / DURATION);
        if (duration < 0.0F) {
            isDone = true;
            color.a = 0.0F;
        }
    }

    public void render(SpriteBatch sb) {
        float x = ReflectionHacks.getPrivate(clicky, ClickableUIElement.class, "x");
        float y = ReflectionHacks.getPrivate(clicky, ClickableUIElement.class, "y");
        sb.setColor(color);
        sb.setBlendFunction(770, 1);
        if (region != null) {
            sb.draw(region, x - (float)region.packedWidth / 2.0F, y - (float)region.packedHeight / 2.0F,
                    (float)region.packedWidth / 2.0F, (float)region.packedHeight / 2.0F,
                    (float)region.packedWidth, (float)region.packedHeight, scale, scale, 0.0F);
        } else if (img != null) {
            float halfWidth = (float)img.getWidth() / 2.0F;
            float halfHeight = (float)img.getHeight() / 2.0F;
            sb.draw(img, x - halfWidth + halfWidth * Settings.scale, y - halfHeight + halfHeight * Settings.scale,
                    halfWidth, halfHeight, (float)img.getWidth(), (float)img.getHeight(), scale, scale, 0.0F,
                    0, 0, img.getWidth(), img.getHeight(), false, false);
        }

        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
