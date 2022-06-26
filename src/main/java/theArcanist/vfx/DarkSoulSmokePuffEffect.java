package theArcanist.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;

public class DarkSoulSmokePuffEffect extends AbstractGameEffect {
    private static final float DEFAULT_DURATION = 0.8F;
    private ArrayList<FastDarkSoulSmoke> smoke = new ArrayList();

    public DarkSoulSmokePuffEffect(float x, float y) {
        duration = DEFAULT_DURATION;

        for(int i = 0; i < 20; ++i)
            smoke.add(new FastDarkSoulSmoke(x, y));
    }

    public void update() {
        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F)
            isDone = true;
        else if (duration < 0.7F)
            killSmoke();

        for (FastDarkSoulSmoke b : smoke)
            b.update();
    }

    private void killSmoke() {
        for (FastDarkSoulSmoke s : smoke)
            s.kill();
    }

    public void render(SpriteBatch sb) {
        for (FastDarkSoulSmoke b : smoke)
            b.render(sb);
    }

    public void dispose() {
    }
}
