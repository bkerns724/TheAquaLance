package theAquaLance.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PotionStrings;
import theAquaLance.AquaLanceMod;

public abstract class AbstractEasyPotion extends CustomPotion {
    protected PotionStrings potionStrings;

    public AbstractEasyPotion(String potionID, PotionRarity rarity, PotionSize size, PotionColor color,
                              boolean isThrown, boolean targetRequired) {
        super(CardCrawlGame.languagePack.getPotionString(potionID).NAME, potionID, rarity, size, color);
        potency = getPotency();
        this.isThrown = isThrown;
        this.targetRequired = targetRequired;
        labOutlineColor = AquaLanceMod.AQUALANCE_EYE_COLOR;
    }

    public CustomPotion makeCopy() {
        try {
            return getClass().newInstance();
        } catch (IllegalAccessException | InstantiationException var2) {
            throw new RuntimeException("BaseMod failed to auto-generate makeCopy for potion: " + ID);
        }
    }
}