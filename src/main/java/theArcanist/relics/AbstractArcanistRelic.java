package theArcanist.relics;

import basemod.AutoAdd;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theArcanist.util.TexLoader;

import static theArcanist.ArcanistMod.makeRelicPath;
import static theArcanist.ArcanistMod.modID;

@AutoAdd.Ignore
public abstract class AbstractArcanistRelic extends CustomRelic {
    public AbstractCard.CardColor color;
    public int amount;
    public int amount2;

    public AbstractArcanistRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx) {
        this(setId, tier, sfx, null);
    }

    public AbstractArcanistRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, AbstractCard.CardColor color) {
        super(setId, TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + ".png")), tier, sfx);
        outlineImg = TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + "_outline.png"));
        this.color = color;
    }

    public String getUpdatedDescription() {
        String descString = DESCRIPTIONS[0];
        descString = descString.replace("!R!", "#b" + amount).replace("!R2!", "#b" + amount2);
        return descString;
    }
}