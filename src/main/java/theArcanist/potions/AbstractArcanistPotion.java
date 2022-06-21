package theArcanist.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import theArcanist.ArcanistMod;

public abstract class
AbstractArcanistPotion extends CustomPotion {
    protected PotionStrings potionStrings;
    protected int defaultPotency;
    public AbstractCard cardToPreview = null;

    public AbstractArcanistPotion(String potionID, PotionRarity rarity, PotionSize size, PotionColor color,
                                  boolean isThrown, boolean targetRequired, int defaultPotency) {
        super(CardCrawlGame.languagePack.getPotionString(potionID).NAME, potionID, rarity, size, color);
        labOutlineColor = ArcanistMod.ARCANIST_EYE_COLOR;
        this.isThrown = isThrown;
        this.targetRequired = targetRequired;
        this.defaultPotency = defaultPotency;
        potency = getPotency();
        potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
        setDescription();
    }

    public void setDescription() {
        if (potency != 1 && potionStrings.DESCRIPTIONS.length > 1)
            description = potionStrings.DESCRIPTIONS[1].replace("!P!", "#b" + potency);
        else
            description = potionStrings.DESCRIPTIONS[0].replace("!P!", "#b" + potency);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    public CustomPotion makeCopy() {
        try {
            return getClass().newInstance();
        } catch (IllegalAccessException | InstantiationException var2) {
            throw new RuntimeException("BaseMod failed to auto-generate makeCopy for potion: " + ID);
        }
    }

    @Override
    public int getPotency(int i) {
        return defaultPotency;
    }
}