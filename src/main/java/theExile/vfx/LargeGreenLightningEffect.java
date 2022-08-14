package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class LargeGreenLightningEffect extends AbstractGameEffect {
    private AbstractMonster m;
    private static final float DURATION = 0.5f;
    private static final float INTERVAL = 0.12f;
    private int boltCount;

    public LargeGreenLightningEffect(AbstractMonster m) {
        this.m = m;
        boltCount = 0;
        duration = startingDuration = DURATION;
    }

    @Override
    public void update() {
        if (duration == startingDuration)
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.LONG, false);

        duration -= Gdx.graphics.getDeltaTime();
        if (DURATION - INTERVAL*boltCount > duration) {
            boltCount++;
            float x = m.drawX + MathUtils.random(-100, 100);
            AbstractDungeon.topLevelEffectsQueue.add(new GreenLightningEffect(x, m.drawY, false));
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
