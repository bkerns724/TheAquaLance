package theAquaLance.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.powers.SoakedPower;

import static theAquaLance.AquaLanceMod.AQUALANCE_EYE_COLOR;
import static theAquaLance.util.Wiz.applyToEnemy;

public class WaterBucket extends AbstractEasyPotion {
    public static final String POTION_ID = AquaLanceMod.makeID("WaterBucket");
    private static final int DROWN_AMOUNT = 4;

    public WaterBucket() {
        super(POTION_ID, PotionRarity.UNCOMMON, PotionSize.JAR, PotionColor.BLUE, true, true);
        PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
        String[] potionDesc = potionStrings.DESCRIPTIONS;
        description = potionDesc[0] + potency + potionDesc[1];
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(BaseMod.getKeywordTitle(SoakedPower.POWER_ID), BaseMod.getKeywordDescription(SoakedPower.POWER_ID)));
        labOutlineColor = AQUALANCE_EYE_COLOR;
    }

    public void use(AbstractCreature target) {
        applyToEnemy((AbstractMonster) target, new SoakedPower(target, potency), AquaLanceMod.Enums.WATER);
    }

    public int getPotency(int ascensionLevel) { return DROWN_AMOUNT; }
}