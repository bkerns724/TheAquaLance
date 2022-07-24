package theExile.relics;

import basemod.AutoAdd;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import theExile.ExileMod;
import theExile.util.TexLoader;

import static theExile.ExileMod.makeRelicPath;
import static theExile.ExileMod.modID;
import static theExile.util.Wiz.adp;

@AutoAdd.Ignore
public abstract class AbstractExileRelic extends CustomRelic {
    public AbstractCard.CardColor color;
    protected int amount;
    protected int amount2;
    protected AbstractCard cardToPreview = null;
    private static final float BOX_W = 360.0F * Settings.scale;

    public AbstractExileRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx) {
        this(setId, tier, sfx, null);
    }

    public AbstractExileRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, AbstractCard.CardColor color) {
        super(setId, TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + ".png")), tier, sfx);
        outlineImg = TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + "_outline.png"));
        this.color = color;
    }

    @Override
    public String getUpdatedDescription() {
        String descString = DESCRIPTIONS[0];
        return descString.replace("!R!", "#b" + amount).replace("!R2!", "#b" + amount2);
    }

    public void setUpdatedDescription() {
        description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    @Override
    public void renderTip(SpriteBatch sb)
    {
        super.renderTip(sb);
        if (cardToPreview != null)
            renderCardPreview(sb, false);
    }

    @Override
    public void renderBossTip(SpriteBatch sb)
    {
        super.renderBossTip(sb);
        if (cardToPreview != null)
            renderCardPreview(sb, true);
    }

    private void renderCardPreview(SpriteBatch sb, boolean boss) // Needs implementation for shops, elite drops, and chests
    {
        int x = InputHelper.mX;
        int y = InputHelper.mY;

        if (CardCrawlGame.mainMenuScreen.screen == MainMenuScreen.CurScreen.RELIC_VIEW) {
            ExileMod.logger.info("COMPENDIUM");
            cardToPreview.current_x = Settings.WIDTH - 380 * Settings.scale;
            cardToPreview.current_y = Settings.HEIGHT * 0.65F - cardToPreview.hb.width / 2.0F;
            cardToPreview.drawScale = 1;
        } else if (adp() != null && adp().relics.contains(this)) {
            ExileMod.logger.info("TOP ROW");
            if (x < 1400.0F * Settings.scale)
                cardToPreview.current_x = x + BOX_W + cardToPreview.hb.width*0.7f;
            else
                cardToPreview.current_x = x - BOX_W - cardToPreview.hb.width*0.7f;

            cardToPreview.current_y = y - cardToPreview.hb.height*0.5f;

            if (cardToPreview.current_y - cardToPreview.hb.height*0.5f < 0)
                cardToPreview.current_y = cardToPreview.hb.height*0.5f;
            else if (cardToPreview.current_y >= Settings.HEIGHT - hb.height*0.5f)
                cardToPreview.current_y = Settings.HEIGHT - hb.height*0.5f;

            cardToPreview.drawScale = 0.7f;
        } else if (boss) {
            ExileMod.logger.info("BOSS");
            cardToPreview.current_x = Settings.WIDTH*0.94F - cardToPreview.hb.width/2.0F;
            cardToPreview.current_y = Settings.HEIGHT*0.6F - cardToPreview.hb.height/2.0F;
            cardToPreview.drawScale = 1;
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP) {
            ExileMod.logger.info("SHOP");
            cardToPreview.current_x = x + BOX_W + cardToPreview.hb.width*0.7f;

            cardToPreview.current_y = y;

            if (cardToPreview.current_y - cardToPreview.hb.height*0.5f < 0)
                cardToPreview.current_y = cardToPreview.hb.height*0.5f;
            else if (cardToPreview.current_y >= Settings.HEIGHT - hb.height*0.5f)
                cardToPreview.current_y = Settings.HEIGHT - hb.height*0.5f;

            cardToPreview.drawScale = 0.7f;
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            ExileMod.logger.info("COMBAT REWARD");
            cardToPreview.current_x = x - BOX_W - cardToPreview.hb.width*0.7f;

            cardToPreview.current_y = y;

            if (cardToPreview.current_y - cardToPreview.hb.height*0.5f < 0)
                cardToPreview.current_y = cardToPreview.hb.height*0.5f;
            else if (cardToPreview.current_y >= Settings.HEIGHT - hb.height*0.5f)
                cardToPreview.current_y = Settings.HEIGHT - hb.height*0.5f;

            cardToPreview.drawScale = 0.7f;
        }
        cardToPreview.render(sb);
    }
}