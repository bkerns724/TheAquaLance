package theExile.util;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.FtueTip;
import theExile.ExileMod;

import static com.megacrit.cardcrawl.core.Settings.CREAM_COLOR;
import static com.megacrit.cardcrawl.core.Settings.GOLD_COLOR;
import static theExile.ExileMod.makeID;

public class ClickyFtue extends FtueTip {
    private static final Texture texture = TexLoader.getTexture(ExileMod.FTUE_IMG);
    private static final UIStrings strings = CardCrawlGame.languagePack.getUIString(makeID("ClickyFtue"));

    private static final int W = 622;
    private static final int H = 284;

    private static final int W_BUTTON = 210;
    private static final int H_BUTTON = 52;

    private final ClickyGotItButton clickyButton;

    public ClickyFtue(String header, String body, float x, float y) {
        ReflectionHacks.setPrivate(this, FtueTip.class, "header", header);
        ReflectionHacks.setPrivate(this, FtueTip.class, "body", body);
        ReflectionHacks.setPrivate(this, FtueTip.class, "x", x);
        ReflectionHacks.setPrivate(this, FtueTip.class, "y", y);
        clickyButton = new ClickyGotItButton(x, y, Settings.scale*1.5f);
        AbstractDungeon.player.releaseCard();
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }

        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.FTUE;
        AbstractDungeon.overlayMenu.showBlackScreen();

        ReflectionHacks.setPrivate(this, FtueTip.class, "type", ExileMod.Enums.CLICKY_IMAGE);
    }

    public void update() {
        clickyButton.update();
        if (clickyButton.hb.clicked || CInputActionSet.proceed.isJustPressed()) {
            CInputActionSet.proceed.unpress();
            CardCrawlGame.sound.play("DECK_OPEN");
            AbstractDungeon.closeCurrentScreen();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        float x = Settings.WIDTH/2f;
        float y = Settings.HEIGHT/2f;
        String header = strings.TEXT[0];
        String body = strings.TEXT[1];

        float scale = Settings.scale*1.5f;

        sb.setColor(Color.WHITE);
        sb.draw(ImageMaster.FTUE, x - W/2f, y - H/2f, W/2f, H/2f, W, H,
                scale, scale, 0.0F, 0, 0, W, H, false, false);

        clickyButton.render(sb);

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, LABEL[0] + header,
                x - 190.0F * scale, y + 80.0F * scale, GOLD_COLOR);
        FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, body, x - 250.0F * scale,
                y + 20.0F * scale, 225.0F * scale, 26.0F * scale, CREAM_COLOR);
        FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, LABEL[1],
                x + 194.0F * scale, y - 150.0F * scale, GOLD_COLOR);

        scale = Settings.scale;

        sb.draw(texture, x - 10f*scale, y - 115f*scale, 0, 0F,
                texture.getWidth(), texture.getHeight(), scale, scale, 0, 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);
    }
}
