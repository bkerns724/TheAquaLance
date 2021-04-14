package theAquaLance.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.powers.DrowningPower;

import static theAquaLance.util.Wiz.applyToEnemy;

public class WaterBucket extends AbstractEasyPotion {
    public static final String POTION_ID = AquaLanceMod.makeID("WaterBucket");
    private static final int DROWN_AMOUNT = 4;

    public WaterBucket() {
        super(POTION_ID, PotionRarity.UNCOMMON, PotionSize.JAR, PotionColor.BLUE, false, false);
        String[] potionDesc = potionStrings.DESCRIPTIONS;
        description = potionDesc[0] + potency + potionDesc[1];
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(BaseMod.getKeywordTitle(DrowningPower.POWER_ID), BaseMod.getKeywordDescription(DrowningPower.POWER_ID)));
    }

    public void use(AbstractCreature target) {
        applyToEnemy((AbstractMonster) target, new DrowningPower(target, potency));
    }

    public int getPotency(int ascensionLevel) { return DROWN_AMOUNT; }
}