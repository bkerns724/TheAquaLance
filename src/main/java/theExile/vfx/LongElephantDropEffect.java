package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theExile.ExileMod;

public class LongElephantDropEffect extends AbstractGameEffect {
    private static final float DURATION = 2.3F;
    private static final float DROP_DURATION = 0.4F;
    private static final Texture img = ImageMaster.loadImage(ExileMod.ELEPHANT_EFFECT_FILE);
    private static final float ELEPHANT_WIDTH = img.getWidth();
    private static final float ELEPHANT_HEIGHT = img.getHeight();
    private static final float START_Y = 1080f + ELEPHANT_HEIGHT/2f;
    private static final float FLOOR_Y = 400f;
    private static final float HIT_X = 1250f;

    private float y;

    public LongElephantDropEffect() {
        startingDuration = DURATION;
        duration = DURATION;
        scale = Settings.scale;
        renderBehind = false;
        y = START_Y;
    }

    public void update() {
        if (duration == startingDuration)
            CardCrawlGame.sound.playA(ExileMod.ELEPHANT_KEY, 0.1f);

        if (duration < DROP_DURATION)
            y = FLOOR_Y + (START_Y - FLOOR_Y) * duration / DROP_DURATION;

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F) {
            AbstractDungeon.effectsQueue.add(new ElephantSplatEffect(HIT_X, FLOOR_Y));
            isDone = true;
            y = FLOOR_Y;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.setBlendFunction(770, 771);
        sb.draw(img, HIT_X - ELEPHANT_WIDTH/2F, y - ELEPHANT_HEIGHT/2F, ELEPHANT_WIDTH/2F, ELEPHANT_HEIGHT/2F,
                ELEPHANT_WIDTH, ELEPHANT_HEIGHT, scale, scale, rotation, 0, 0, (int)ELEPHANT_WIDTH, (int)ELEPHANT_HEIGHT,
                false, true);
    }

    public void dispose() {
    }
}
