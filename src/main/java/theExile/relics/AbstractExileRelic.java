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
import theExile.util.TexLoader;

import static theExile.ExileMod.makeRelicPath;
import static theExile.ExileMod.modID;

@AutoAdd.Ignore
public abstract class AbstractExileRelic extends CustomRelic {
    public AbstractCard.CardColor color;
    protected int amount;
    protected int amount2;
    protected AbstractCard cardToPreview = null;

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
        if (cardToPreview == null)
            return;

        if (boss) {
            cardToPreview.current_x = Settings.WIDTH*0.94F - cardToPreview.hb.width/2.0F;
            cardToPreview.current_y = Settings.HEIGHT*0.6F - cardToPreview.hb.height/2.0F;
        }
        else if (CardCrawlGame.mainMenuScreen.screen == MainMenuScreen.CurScreen.RELIC_VIEW)
        {
            cardToPreview.current_x = Settings.WIDTH - 380 * Settings.scale;
            cardToPreview.current_y = Settings.HEIGHT * 0.65F - cardToPreview.hb.width/2.0F;
        }
        else if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(relicId))
        {
            if (InputHelper.mX >= 1400.0F * Settings.scale)
            {
                cardToPreview.current_x = InputHelper.mX - 420 * Settings.scale - cardToPreview.hb.width/2.0F;
                cardToPreview.current_y = InputHelper.mY - 80*Settings.scale - cardToPreview.hb.height/2.0F;
            }
            else if (InputHelper.mX < 1100.0F * Settings.scale)
            {
                cardToPreview.current_x = InputHelper.mX + 450 * Settings.scale + cardToPreview.hb.width/2.0F;
                cardToPreview.current_y = InputHelper.mY - 60*Settings.scale - cardToPreview.hb.height/2.0F;
            }
            else
            {
                cardToPreview.current_x = InputHelper.mX - 50 * Settings.scale - cardToPreview.hb.width/2.0F;
                cardToPreview.current_y = InputHelper.mY - 60*Settings.scale - cardToPreview.hb.height/2.0F;
            }
        }
        cardToPreview.drawScale = 1;
        cardToPreview.render(sb);
    }
}