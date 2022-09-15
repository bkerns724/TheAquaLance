package theExile.vfx;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theExile.ExileMod;

public class BeesChannelEffect extends AbstractGameEffect {
    private static final float DURATION = 1.0f;
    private long id;

    public BeesChannelEffect() {
        duration = startingDuration = DURATION;
    }

    public void update() {
        if (duration == DURATION)
            id = CardCrawlGame.sound.play(ExileMod.BEES_KEY, 0.1f);
        else if (duration < 0) {
            CardCrawlGame.sound.fadeOut(ExileMod.BEES_KEY, id);
            isDone = true;
        }
        duration -= Gdx.graphics.getDeltaTime();
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
