package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theExile.ExileMod;
import theExile.util.TexLoader;

import java.util.ArrayList;

import static theExile.util.Wiz.forAllMonstersLiving;

public class DarkLargeEffect extends AbstractGameEffect {
    public static final float EFFECT_DUR = 2.0F;
    public static final float DUR_BEFORE_IMPACT = 1.0F;
    public static final float DUR_DIFFERENCE = EFFECT_DUR - DUR_BEFORE_IMPACT;
    private static TextureAtlas.AtlasRegion img;
    private static TextureAtlas.AtlasRegion img2;
    private boolean biteHook;
    private AbstractMonster m;
    private static TextureAtlas.AtlasRegion top;
    private static TextureAtlas.AtlasRegion bot;
    private ArrayList<DarkBiteEffect> bites;

    public DarkLargeEffect(AbstractMonster mon) {
        m = mon;
        biteHook = false;
        if (img == null) {
            Texture texture = TexLoader.getTexture(ExileMod.DARK_L_EFFECT_FILE);
            img = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        }
        if (img2 == null) {
            Texture texture = TexLoader.getTexture(ExileMod.DARK_L2_EFFECT_FILE);
            img2 = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        }
        if (top == null) {
            top = ImageMaster.vfxAtlas.findRegion("combat/biteTop");
            bot = ImageMaster.vfxAtlas.findRegion("combat/biteBot");
        }

        scale = Settings.scale;
        duration = EFFECT_DUR;
        color = Color.WHITE.cpy();
        renderBehind = false;

        bites = new ArrayList<>();
    }

    @Override
    public void update() {
        if (duration == EFFECT_DUR) {
            CardCrawlGame.sound.play("NECRONOMICON");
            CardCrawlGame.sound.play("NECRONOMICON");
            CardCrawlGame.sound.play("NECRONOMICON");
        }

        if (duration - DUR_DIFFERENCE < 0.3f && !biteHook) {
            biteHook = true;
            if (m != null) {
                DarkBiteEffect bite = new DarkBiteEffect(m.hb.cX, m.hb.cY);
                bites.add(bite);
            } else {
                forAllMonstersLiving(m -> {
                    DarkBiteEffect bite = new DarkBiteEffect(m.hb.cX, m.hb.cY);
                    bites.add(bite);
                });
            }
        }

        if ((duration - DUR_DIFFERENCE)*2.0 > DUR_BEFORE_IMPACT)
            color.a = Interpolation.fade.apply(1.0F, 0.4F,
                    ((duration - DUR_DIFFERENCE)*2.0f - DUR_BEFORE_IMPACT)/DUR_BEFORE_IMPACT);
        else if (duration*2.0f <= DUR_DIFFERENCE)
            color.a = Interpolation.fade.apply(0.4F, 1.0F, (duration*2.0f)/DUR_DIFFERENCE);
        else
            color.a = 1.0f;

        if (duration < 0.0f) {
            isDone = true;
            color.a = 0.0f;
        }

        for (DarkBiteEffect bite : bites)
            if (!bite.isDone)
                bite.update();

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
        for (DarkBiteEffect bite : bites)
            if (!bite.isDone)
                bite.render(sb);
    }

    @Override
    public void dispose() { }

    public class DarkBiteEffect {
        private float x;
        private float y;
        private float sY;
        private float dY;
        private float y2;
        private float sY2;
        private float dY2;
        private static final float DUR = 1.0F;
        private boolean playedSfx;
        private boolean isDone;
        private float duration;
        private float startingDuration;
        private Color color;
        private float scale;

        public DarkBiteEffect(float x, float y) {
            playedSfx = false;

            scale = 2.0F * Settings.scale;
            this.x = x - (float)top.packedWidth / 2.0F;
            sY = y - (float)top.packedHeight / 2.0F + 150.0F * scale;
            dY = y;
            this.y = sY;
            sY2 = y - (float)(top.packedHeight / 2) - 100.0F * scale;
            dY2 = y - 90.0F * scale;
            y2 = sY2;
            duration = startingDuration = DUR;
            color = Color.BLACK.cpy();
        }

        public void update() {
            duration -= Gdx.graphics.getDeltaTime();
            if (duration < startingDuration - 0.3F && !playedSfx) {
                playedSfx = true;
                CardCrawlGame.sound.play("EVENT_VAMP_BITE", 0.15F);
            }

            if (duration > startingDuration / 2.0F) {
                color.a = Interpolation.fade.apply(1.0F, 0.0F, (duration - 0.5F) * 2.0F);
                y = Interpolation.bounceIn.apply(dY, sY, (duration - 0.5F) * 2.0F);
                y2 = Interpolation.bounceIn.apply(dY2, sY2, (duration - 0.5F) * 2.0F);
            } else {
                color.a = Interpolation.fade.apply(0.0F, 1.0F, duration * 2.0F);
                y = Interpolation.fade.apply(sY, dY, duration * 2.0F);
                y2 = Interpolation.fade.apply(sY2, dY2, duration * 2.0F);
            }

            if (duration < 0.0F)
                isDone = true;
        }

        public void render(SpriteBatch sb) {
            sb.setColor(color);
            sb.draw(top, x, y, (float)top.packedWidth / 2.0F, (float)top.packedHeight / 2.0F,
                    (float)top.packedWidth, (float)top.packedHeight, scale, scale, 0.0F);
            sb.draw(bot, x, y2, (float)top.packedWidth / 2.0F, (float)top.packedHeight / 2.0F,
                    (float)top.packedWidth, (float)top.packedHeight, scale, scale, 0.0F);
        }

        public void dispose() {
        }
    }
}
