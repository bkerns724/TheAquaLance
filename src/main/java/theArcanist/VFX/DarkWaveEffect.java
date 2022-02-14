package theArcanist.VFX;


import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.IronWaveParticle;

public class DarkWaveEffect extends AbstractGameEffect {
    private float waveTimer = 0.0F;
    private float x;
    private float y;
    private float cX;
    private static final float WAVE_INTERVAL = 0.03F;

    public DarkWaveEffect(float x, float y, float cX) {
        this.x = x + 120.0F * Settings.scale;
        this.y = y - 20.0F * Settings.scale;
        this.cX = cX;
    }

    public void update() {
        waveTimer -= Gdx.graphics.getDeltaTime();
        if (waveTimer < 0.0F) {
            waveTimer = WAVE_INTERVAL;
            x += 160.0F * Settings.scale;
            y -= 15.0F * Settings.scale;
            DarkWaveParticle wave = new DarkWaveParticle(x, y);
            AbstractDungeon.effectsQueue.add(wave);
            if (x > cX) {
                isDone = true;
                CardCrawlGame.sound.playA("ATTACK_DAGGER_6", -0.3F);
            }
        }

    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
