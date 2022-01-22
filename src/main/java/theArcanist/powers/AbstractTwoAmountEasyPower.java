package theArcanist.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

public abstract class AbstractTwoAmountEasyPower extends TwoAmountPower {

    public AbstractTwoAmountEasyPower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        this.ID = id;
        this.isTurnBased = isTurnBased;
        this.owner = owner;
        this.amount = amount;
        this.type = powerType;

        String textureString = ArcanistMod.modID + "Resources/images/powers/" + ID.replaceAll(ArcanistMod.modID +":",  "") + "32.png";

        Texture normalTexture = TexLoader.getTexture(textureString);
        Texture hiDefImage = TexLoader.getTexture(ArcanistMod.modID + "Resources/images/powers/" + ID.replaceAll(ArcanistMod.modID +":", "") + "84.png");
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
}