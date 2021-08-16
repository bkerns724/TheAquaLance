package theAquaLance.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import theAquaLance.AquaLanceMod;
import theAquaLance.powers.IntelligencePower;
import theAquaLance.powers.LoseIntPower;

import static theAquaLance.util.Wiz.adp;
import static theAquaLance.util.Wiz.applyToSelf;

public class InsightPotion extends AbstractEasyPotion {
    public static final String POTION_ID = AquaLanceMod.makeID("InsightPotion");
    private static final int INT_AMOUNT = 5;

    public InsightPotion() {
        super(POTION_ID, PotionRarity.COMMON, PotionSize.BOLT, PotionColor.WHITE, false, false);
        PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
        String[] potionDesc = potionStrings.DESCRIPTIONS;
        description = potionDesc[0] + potency + potionDesc[1] + potency + potionDesc[2];
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(BaseMod.getKeywordTitle("aqualancemod:Intelligence"),
                BaseMod.getKeywordDescription("aqualancemod:Intelligence")));
    }

    public void use(AbstractCreature target) {
        applyToSelf(new IntelligencePower(adp(), potency));
        applyToSelf(new LoseIntPower(adp(), potency));
    }

    public int getPotency(int ascensionLevel) { return INT_AMOUNT; }
}