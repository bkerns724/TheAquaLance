package theArcanist.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

public class DarkLargeEffect extends AbstractGameEffect {
    public static final float EFFECT_DUR = 2.0F;
    public static final float DUR_BEFORE_IMPACT = 1.0F;
    public static final float DUR_DIFFERENCE = EFFECT_DUR - DUR_BEFORE_IMPACT;
    private static TextureAtlas.AtlasRegion img;
    private static TextureAtlas.AtlasRegion img2;

    public DarkLargeEffect() {
        if (img == null) {
            Texture texture = TexLoader.getTexture(ArcanistMod.DARK_L_EFFECT_FILE);
            img = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        }
        if (img2 == null) {
            Texture texture = TexLoader.getTexture(ArcanistMod.DARK_L2_EFFECT_FILE);
            img2 = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        }

        scale = Settings.scale;
        duration = EFFECT_DUR;
        color = Color.WHITE.cpy();
        renderBehind = false;
    }

    @Override
    public void update() {
        if (duration == EFFECT_DUR) {
            CardCrawlGame.music.silenceBGMInstantly();
            CardCrawlGame.sound.play("NECRONOMICON");
        }

        if ((duration - DUR_DIFFERENCE)*2.0 > DUR_BEFORE_IMPACT)
            color.a = Interpolation.fade.apply(1.0F, 0.0F,
                    ((duration - DUR_DIFFERENCE)*2.0f - DUR_BEFORE_IMPACT)/DUR_BEFORE_IMPACT);
        else if (duration*2.0f <= DUR_DIFFERENCE)
            color.a = Interpolation.fade.apply(0.0F, 1.0F, (duration*2.0f)/DUR_DIFFERENCE);
        else
            color.a = 1.0f;

        if (duration < 0.0f) {
            isDone = true;
            color.a = 0.0f;
            CardCrawlGame.music.unsilenceBGM();
        }

        duration -= Gdx.graphics.getDeltaTime();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setBlendFunction(GL20.GL_ONE_MINUS_DST_COLOR, GL20.GL_ONE_MINUS_SRC_COLOR);
        sb.setColor(color);
        sb.draw(img, 0, 0, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth, (float)img.packedHeight, scale, scale, 0.0f);
        sb.setBlendFunction(770, 771);
        sb.draw(img2, 0, 0, (float)img2.packedWidth / 2.0F, (float)img2.packedHeight / 2.0F,
                (float)img2.packedWidth, (float)img2.packedHeight, scale, scale, 0.0f);
    }

    @Override
    public void dispose() { }
}
