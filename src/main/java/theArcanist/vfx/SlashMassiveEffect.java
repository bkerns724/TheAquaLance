package theArcanist.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SlashMassiveEffect extends AbstractGameEffect {
    public static final float EFFECT_DUR = 0.4F;
    public static final float FADED_IN = 0.28f;
    public static final float HIT_TIME = 0.2F;
    public static final float FADE_OUT = 0.12f;
    private static TextureAtlas.AtlasRegion img;
    private AbstractMonster m;
    private boolean hitHook;
    private float targetX;
    private float targetY;
    private float startX;
    private float startY;
    private float middleX;
    private float middleY;
    private float x;
    private float y;

    public SlashMassiveEffect(AbstractMonster mon) {
        m = mon;

        if (img == null)
            img = ImageMaster.ATK_SLASH_HEAVY;
        if (!img.isFlipX())
            img.flip(true, false);

        x = startX;
        y = startY;
        hitHook = false;

        scale = Settings.scale*2f;
        duration = EFFECT_DUR;
        color = new Color(1f, 1f, 1f, 0f);
        renderBehind = false;
        rotation = 0f;

        middleX = m.hb.cX - img.packedWidth/2f;
        middleY = m.hb.cY - img.packedHeight/2f;
        targetX = middleX + 125f*Settings.scale;
        targetY = middleY - 175f*Settings.scale;
        startX = middleX - 275f*Settings.scale;
        startY = middleY + 225f*Settings.scale;
    }

    @Override
    public void update() {
        if (duration >= FADED_IN)
            color.a = Interpolation.fade.apply(1f, 0f, (duration - FADED_IN)/(EFFECT_DUR - FADED_IN));
        else if (duration < FADED_IN && duration > FADE_OUT)
            color.a = 1.0f;
        else if (duration <= FADE_OUT)
            color.a = Interpolation.fade.apply(0f, 1f, duration/FADE_OUT);

        if (!hitHook) {
            hitHook = true;
            for (int i = 0; i < 5; i++)
                CardCrawlGame.sound.play("ATTACK_HEAVY");
        }

        x = Interpolation.linear.apply(targetX, startX, duration/EFFECT_DUR);
        y = Interpolation.linear.apply(targetY, startY, duration/EFFECT_DUR);

        if (duration < 0.0f)
            isDone = true;

        duration -= Gdx.graphics.getDeltaTime();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(color);
        sb.draw(img, x, y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F,
                (float)img.packedWidth, (float)img.packedHeight, scale, scale, rotation);
        sb.setBlendFunction(770, 771);
    }

    @Override
    public void dispose() { }
}
