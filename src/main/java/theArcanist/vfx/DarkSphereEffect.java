package theArcanist.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

public class DarkSphereEffect extends AbstractGameEffect {
    public static final float EFFECT_DUR = 0.6F;
    public static final float DUR_BEFORE_IMPACT = 0.3F;
    public static final float DUR_DIFFERENCE = EFFECT_DUR - DUR_BEFORE_IMPACT;
    private float x;
    private float y;
    private static TextureAtlas.AtlasRegion img;
    private boolean impactHook;

    public DarkSphereEffect(float x, float y) {
        impactHook = false;
        if (img == null) {
            Texture texture = TexLoader.getTexture(ArcanistMod.DARK_M_EFFECT_FILE);
            img = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        }

        scale = Settings.scale;
        this.y = y - (float) img.packedHeight/2.0f;
        this.x = x - (float) img.packedWidth/2.0f;
        duration = EFFECT_DUR;
        color = Color.WHITE.cpy();
        renderBehind = false;
    }

    @Override
    public void update() {
        if (duration == EFFECT_DUR)
            CardCrawlGame.sound.playA("SINGING_BOWL", -0.5F);
        duration -= Gdx.graphics.getDeltaTime();

        if (duration > DUR_DIFFERENCE) {
            color.r = (duration - DUR_DIFFERENCE) / (DUR_BEFORE_IMPACT);
            color.g = (duration - DUR_DIFFERENCE) / (DUR_BEFORE_IMPACT);
            color.b = (duration - DUR_DIFFERENCE) / (DUR_BEFORE_IMPACT);
        }

        if (duration < DUR_DIFFERENCE + 0.1f) {
            if (!impactHook) {
                impactHook = true;
                CardCrawlGame.sound.play("EVENT_VAMP_BITE", -0.15F);
            }
        }
        if (duration < DUR_DIFFERENCE / 2.0f)
            color.a = Interpolation.fade.apply(0.0F, 1.0F, duration/(DUR_DIFFERENCE*0.5f));

        if (duration < 0.0f) {
            isDone = true;
            color.a = 0.0f;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 771);
        sb.setColor(color);
        sb.draw(img, x, y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth, (float)img.packedHeight,
                scale, scale, 0.0f);
        sb.setBlendFunction(770, 771);
    }

    @Override
    public void dispose() { }
}
