package theAquaLance.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theAquaLance.AquaLanceMod;
import theAquaLance.powers.StreamlinedPower;

import static theAquaLance.util.Wiz.*;

public class SharkFinPotion extends AbstractEasyPotion {
    public static final String POTION_ID = AquaLanceMod.makeID("SharkFinPotion");
    private static final int STREAMLINE_AMT = 6;

    public SharkFinPotion() {
        super(POTION_ID, PotionRarity.UNCOMMON, PotionSize.JAR, PotionColor.BLUE, false, false);
        String[] potionDesc = potionStrings.DESCRIPTIONS;
        description = potionDesc[0] + potency + potionDesc[1];
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(BaseMod.getKeywordTitle(StreamlinedPower.POWER_ID), BaseMod.getKeywordDescription(StreamlinedPower.POWER_ID)));
    }

    public void use(AbstractCreature target) {
        applyToSelf(new StreamlinedPower(adp(), STREAMLINE_AMT));
    }

    public int getPotency(int ascensionLevel) { return STREAMLINE_AMT; }
}