package theExile.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theExile.ExileMod;

public class ElephantSplatEffect extends AbstractGameEffect {
    private static final float DURATION = 0.5F;

    private final float xCenter;
    private final float yCenter;

    public ElephantSplatEffect(float x, float y) {
        xCenter = x;
        yCenter = y;
        startingDuration = DURATION;
        duration = DURATION;
        scale = Settings.scale;
        renderBehind = false;
        ExileMod.logger.info(x);
        ExileMod.logger.info(y);
    }

    public void update() {
        if (duration == startingDuration) {
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_HEAD_FILE, xCenter + 250, yCenter - 115));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_LEG_ONE_FILE, xCenter + 98, yCenter + 132));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_LEG_TWO_FILE, xCenter - 25, yCenter + 160));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_LEG_THREE_FILE, xCenter - 170, yCenter + 165));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_LEG_FOUR_FILE, xCenter - 270, yCenter + 156));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_TAIL_FILE, xCenter - 250, yCenter - 5));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_RUMP_FILE, xCenter - 195, yCenter - 163));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_TRUNK_FILE, xCenter + 265, yCenter + 97));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_EAR_TOP_FILE, xCenter + 87, yCenter + -187));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_EAR_BOTTOM_FILE, xCenter + 113, yCenter - 55));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_TOP_TORSO_FILE, xCenter - 62, yCenter - 162));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_LEFT_TORSO_FILE, xCenter - 140, yCenter - 58));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ExileMod.ELEPHANT_RIGHT_TORSO_FILE, xCenter - 15, yCenter - 5));
        }

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0)
            isDone = true;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    public void dispose() {
    }
}
