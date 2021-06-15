package theAquaLance.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.powers.HobbledPower;

import static theAquaLance.util.Wiz.*;

public class HobblePotion extends AbstractEasyPotion {
    public static final String POTION_ID = AquaLanceMod.makeID("HobblePotion");
    private static final int HOBBLE_AMOUNT = 3;

    public HobblePotion() {
        super(POTION_ID, PotionRarity.UNCOMMON, PotionSize.SPHERE, PotionColor.GREEN, true, true);
        PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
        String[] potionDesc = potionStrings.DESCRIPTIONS;
        description = potionDesc[0] + potency + potionDesc[1];
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(BaseMod.getKeywordTitle("aqualancemod:Hobble"), BaseMod.getKeywordDescription("aqualancemod:Hobble")));
    }

    public void use(AbstractCreature target) {
        applyToEnemy((AbstractMonster) target, new HobbledPower(target, potency));
    }

    public int getPotency(int ascensionLevel) { return HOBBLE_AMOUNT; }
}