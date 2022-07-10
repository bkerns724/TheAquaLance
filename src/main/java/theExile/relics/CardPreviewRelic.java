package theExile.relics;

import basemod.AutoAdd;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;

import java.util.ArrayList;

@AutoAdd.Ignore
public abstract class CardPreviewRelic extends CustomRelic {
    public AbstractCard cardToPreview;
    protected ArrayList<AbstractCard> cardsToPreview = new ArrayList<>();
    private float rotationTimer = 0;
    protected int previewIndex = 0;

    public CardPreviewRelic(String id, Texture texture, RelicTier tier, LandingSound sfx) {
        super(id, texture, tier, sfx);
    }

    public CardPreviewRelic(String id, Texture texture, Texture outline, RelicTier tier, LandingSound sfx) {
        super(id, texture, outline, tier, sfx);
    }

    public CardPreviewRelic(String id, String imgName, RelicTier tier, LandingSound sfx) {
        super(id, imgName, tier, sfx);
    }

    @Override
    public void renderTip(SpriteBatch sb)
    {
        super.renderTip(sb);
        renderCardPreview(sb, false);
    }

    @Override
    public void renderBossTip(SpriteBatch sb)
    {
        super.renderBossTip(sb);
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

    protected float getRotationTimeNeeded() {
        return 1f;
    }

    public void update() {
        super.update();
        if (!cardsToPreview.isEmpty()) {
            if (hb.hovered) {
                if (rotationTimer <= 0F) {
                    rotationTimer = getRotationTimeNeeded();
                    if (previewIndex >= cardsToPreview.size())
                        previewIndex = 0;
                    cardToPreview = cardsToPreview.get(previewIndex);
                    if (previewIndex == cardsToPreview.size() - 1) {
                        previewIndex = 0;
                    } else {
                        previewIndex++;
                    }
                } else {
                    rotationTimer -= Gdx.graphics.getDeltaTime();
                }
            }
        }
    }
}
