package theExile.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

public class ClickyGotItButton {
    public Hitbox hb;
    public static final int W = 210;
    public static final int H = 52;
    public float x;
    public float y;
    private final float scale;

    public ClickyGotItButton(float x, float y, float scale) {
        this.scale = scale;
        hb = new Hitbox(220.0F * scale, 60.0F * scale);
        x += 120.0F * scale;
        y -= 160.0F * scale;
        this.x = x;
        this.y = y;
        hb.move(x, y);
    }

    public void update() {
        hb.update();
        if (hb.justHovered)
            CardCrawlGame.sound.play("UI_HOVER");

        if (hb.hovered && InputHelper.justClickedLeft)
            hb.clickStarted = true;
    }

    public void render(SpriteBatch sb) {
        if (hb.hovered) {
            sb.setBlendFunction(770, 1);
            sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.75F));
        }
        sb.draw(ImageMaster.FTUE_BTN, this.x - W / 2f, this.y - H / 2f, W / 2f, H / 2f,
                W, H, scale, scale, 0.0F, 0, 0, W, H, false, false);

        sb.setColor(Color.WHITE);
        sb.setBlendFunction(770, 771);

        hb.render(sb);
    }
}