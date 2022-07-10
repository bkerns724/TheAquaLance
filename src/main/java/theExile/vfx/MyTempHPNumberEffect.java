package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class MyTempHPNumberEffect extends AbstractGameEffect {
    private static final float EFFECT_DUR = 1.2F;
    private float x;
    private float y;
    private float vX;
    private float vY;
    private static final float OFFSET_Y;
    private static final float GRAVITY_Y;
    private int number;
    private float scale = 1.0F;

    public MyTempHPNumberEffect(float x, float y, int number) {
        duration = EFFECT_DUR;
        startingDuration = EFFECT_DUR;
        this.x = x;
        this.y = y + OFFSET_Y;
        vX = MathUtils.random(100.0F * Settings.scale, 150.0F * Settings.scale);
        if (MathUtils.randomBoolean()) {
            vX = -vX;
        }

        vY = MathUtils.random(400.0F * Settings.scale, 500.0F * Settings.scale);
        this.number = number;
        color = Color.YELLOW.cpy();
    }

    public void update() {
        x += Gdx.graphics.getDeltaTime() * vX;
        y += Gdx.graphics.getDeltaTime() * vY;
        vY += Gdx.graphics.getDeltaTime() * GRAVITY_Y;
        super.update();
        scale = Settings.scale * duration / 1.2F * 3.0F;
        if (scale <= 0.0F) {
            scale = 0.01F;
        }

    }

    public void render(SpriteBatch sb) {
        FontHelper.damageNumberFont.getData().setScale(scale);
        FontHelper.renderFontCentered(sb, FontHelper.damageNumberFont, Integer.toString(number), x, y, color);
    }

    public void dispose() {
    }

    static {
        OFFSET_Y = 150.0F * Settings.scale;
        GRAVITY_Y = -2000.0F * Settings.scale;
    }
}