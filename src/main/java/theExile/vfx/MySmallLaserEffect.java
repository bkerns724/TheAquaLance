package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class MySmallLaserEffect extends AbstractGameEffect {
    private final float sX;
    private final float sY;
    private final float dst;
    private static final float DUR = 0.5F;
    private static TextureAtlas.AtlasRegion img;

    public MySmallLaserEffect(float sX, float sY, float dX, float dY) {
        if (img == null) {
            img = ImageMaster.vfxAtlas.findRegion("combat/laserThin");
        }

        this.sX = sX;
        this.sY = sY;
        dst = Vector2.dst(sX, sY, dX, dY) / scale;
        color = Color.RED.cpy();
        duration = startingDuration = DUR;
        rotation = MathUtils.atan2(dY - sY, dX - sX);
        rotation *= 180f/MathUtils.PI;
    }

    public void update() {
        duration -= Gdx.graphics.getDeltaTime();
        if (duration > startingDuration / 2.0F) {
            color.a = Interpolation.pow2In.apply(1.0F, 0.0F, (duration - 0.25F) * 8.0F);
        } else {
            color.a = Interpolation.bounceIn.apply(0.0F, 1.0F, duration * 8.0F);
        }

        if (duration < 0.0F) {
            isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 771);
        color = Color.YELLOW.cpy();
        color.a = 0.5f;
        sb.setColor(color);
        sb.draw(img, sX + 25*MathUtils.sinDeg(rotation)*scale, sY - 25f*MathUtils.cosDeg(rotation)*scale,
                0.0F,0f, dst,75.0F + MathUtils.random(-3f, 3f), scale + MathUtils.random(-0.01F, 0.01F),
                scale, rotation);
        color = Color.ORANGE.cpy();
        color.a = 0.75f;
        sb.setColor(color);
        sb.draw(img, sX + 12.5f*MathUtils.sinDeg(rotation)*scale, sY - 12.5f*MathUtils.cosDeg(rotation)*scale,
                0F,0f, dst, 50.0F + MathUtils.random(-3f, 3f), scale + MathUtils.random(-0.01F, 0.01F),
                scale, rotation);
        color = Color.RED.cpy();
        color.a = 1f;
        sb.setColor(color);
        sb.draw(img, sX, sY, 0.0F,
                0f, dst, 25.0F + MathUtils.random(-3f, 3f), scale + MathUtils.random(-0.01F, 0.01F),
                scale, rotation);

    }

    public void dispose() {
    }
}