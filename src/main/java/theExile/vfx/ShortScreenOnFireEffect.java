package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.GiantFireEffect;

public class ShortScreenOnFireEffect extends AbstractGameEffect {
    private float timer = 0.0F;
    private static final float INTERVAL = 0.05F;
    private static final int FIRE_COUNT = 8;
    private static final float DURATION = 1.25f;

    public ShortScreenOnFireEffect() {
        duration = startingDuration = DURATION;
    }

    public void update() {
        if (duration == startingDuration) {
            CardCrawlGame.sound.play("GHOST_FLAMES");
            AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.FIREBRICK));
        }

        duration -= Gdx.graphics.getDeltaTime();
        timer -= Gdx.graphics.getDeltaTime();
        if (timer < 0.0F) {
            for (int i = 0; i < FIRE_COUNT; i++)
                AbstractDungeon.effectsQueue.add(new GiantFireEffect());
            timer = INTERVAL;
        }

        if (duration < 0.0F)
            isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
