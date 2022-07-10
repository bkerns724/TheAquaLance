package theExile.vfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SoulBlowEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private int effectSize;

    public SoulBlowEffect(float x, float y, int effectSize) {
        this.x = x;
        this.y = y;
        this.effectSize = effectSize;
    }

    public void update() {
        CardCrawlGame.sound.playA("ATTACK_FIRE", 0.3F);
        CardCrawlGame.sound.playA("ATTACK_HEAVY", -0.3F);
        float dst = 180.0F + effectSize * 3.0F;
        AbstractDungeon.effectsQueue.add(new SoulFireballEffect(x - dst * Settings.scale, y,
                x + dst * Settings.scale, y - 50.0F * Settings.scale, effectSize));
        isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
