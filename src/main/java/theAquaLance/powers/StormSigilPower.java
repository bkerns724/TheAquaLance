package theAquaLance.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theAquaLance.util.TexLoader;

import static theAquaLance.util.Wiz.*;

public class StormSigilPower extends AbstractPower {
    public static String POWER_ID = AquaLanceMod.modID + "StormSigil";
    public int amount2;
    public boolean canGoNegative2 = false;
    private static final Color redColor2 = Color.RED.cpy();
    private static final Color greenColor2 = Color.GREEN.cpy();
    private static int idOffset = 0;

    public StormSigilPower(AbstractCreature owner, int amount, int amount2) {
        ID = POWER_ID + idOffset;
        idOffset++;
        type = AbstractPower.PowerType.BUFF;
        isTurnBased = false;

        this.owner = owner;
        this.amount = amount;
        this.amount2 = amount2;

        String textureString = AquaLanceMod.modID + "Resources/images/powers/" + POWER_ID.replaceAll(AquaLanceMod.modID +":",  "") + "32.png";
        AquaLanceMod.logger.info("textureString: " + textureString);

        Texture normalTexture = TexLoader.getTexture(textureString);
        Texture hiDefImage = TexLoader.getTexture(AquaLanceMod.modID + "Resources/images/powers/" + POWER_ID + "84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        this.updateDescription();
    }

    public void onManualDiscard() {
        amount2--;
        if (amount2 == 0) {
            for (AbstractMonster m : getEnemies()) {
                int shardCount = getShardCount(m);
                atb(new LoseHPAction(m, adp(), shardCount*amount));
            }
            atb(new RemoveSpecificPowerAction(adp(), adp(), this));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
        else
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (amount2 > 0) {
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