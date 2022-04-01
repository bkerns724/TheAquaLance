package theArcanist.VFX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class MiasmaEffect extends AbstractGameEffect {
    private float x;
    private float y;

    public MiasmaEffect(float x, float y) {
        this.x = x;
        this.y = y;
        duration = 0.2F;
    }

    public void update() {
        if (duration == 0.2F) {
            CardCrawlGame.sound.play("ATTACK_WHIFF_2");

            for(int i = 0; i < 90; ++i)
                AbstractDungeon.effectsQueue.add(new MiasmaBlurEffect(x, y));
        }

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F) {
            CardCrawlGame.sound.play("APPEAR");
            isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}