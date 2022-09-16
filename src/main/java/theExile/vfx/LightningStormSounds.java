package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class LightningStormSounds extends AbstractGameEffect {
    private static final float DURATION = 0.5f;
    private static final float INTERVAL = 0.12f;
    private int boltCount;

    public LightningStormSounds() {
        duration = startingDuration = DURATION;
        boltCount = 0;
    }

    @Override
    public void update() {
        if (duration == startingDuration)
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.LONG, false);

        duration -= Gdx.graphics.getDeltaTime();
        if (DURATION - INTERVAL*boltCount > duration) {
            boltCount++;
            CardCrawlGame.sound.play("ORB_LIGHTNING_EVOKE");
        }

        if (duration < 0.0F)
            isDone = true;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
