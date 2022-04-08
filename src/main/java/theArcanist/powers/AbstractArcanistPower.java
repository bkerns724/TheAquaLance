package theArcanist.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

import static theArcanist.util.Wiz.replaceLast;

public abstract class AbstractArcanistPower extends AbstractPower {
    public int amount2;
    public boolean isTwoAmount = false;
    public static Color redColor2 = Color.RED.cpy();
    public static Color greenColor2 = Color.GREEN.cpy();
    public boolean canGoNegative2 = false;
    public String[] descriptionArray;

    public AbstractArcanistPower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        this.ID = id;
        this.isTurnBased = isTurnBased;
        this.owner = owner;
        this.amount = amount;
        this.type = powerType;
        amount2 = -1;

        PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(id);
        this.name = powerStrings.NAME;
        descriptionArray = powerStrings.DESCRIPTIONS;

        String textureStringBase = ArcanistMod.modID + "Resources/images/powers/" +
                ID.replaceAll(ArcanistMod.modID +":",  "");
        textureStringBase = replaceLast(textureStringBase, "Power", "");
        String textureString32 = textureStringBase + "32.png";
        String textureString84 = textureStringBase + "84.png";

        Texture normalTexture = TexLoader.getTexture(textureString32);
        Texture hiDefImage = TexLoader.getTexture(textureString84);
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount != 1 && descriptionArray.length > 1)
            description = descriptionArray[1];
        else
            description = descriptionArray[0];
        description = description.replace("!A!", "#b" + amount);
        description = description.replace("!A2!", "#b" + amount2);
    }

    public void onManualDiscard() {}

    public void onDiscardSigil() {}

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (isTwoAmount) {
            if (!isTurnBased) {
                greenColor2.a = c.a;
                c = greenColor2;
            }

            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        } else if (amount2 < 0 && canGoNegative2) {
            redColor2.a = c.a;
            c = redColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        }
    }
}