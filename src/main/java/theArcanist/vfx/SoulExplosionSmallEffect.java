package theArcanist.vfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SoulExplosionSmallEffect extends AbstractGameEffect {
    private static final int EMBER_COUNT = 12;
    private float x;
    private float y;

    public SoulExplosionSmallEffect(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        AbstractDungeon.effectsQueue.add(new DarkSoulSmokePuffEffect(x, y));

        for(int i = 0; i < EMBER_COUNT; ++i)
            AbstractDungeon.effectsQueue.add(new SmokingSoulEmberEffect(x + MathUtils.random(-50.0F, 50.0F) * Settings.scale,
                    y + MathUtils.random(-50.0F, 50.0F) * Settings.scale));

        CardCrawlGame.sound.playA("ATTACK_FIRE", MathUtils.random(-0.2F, -0.1F));
        isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
