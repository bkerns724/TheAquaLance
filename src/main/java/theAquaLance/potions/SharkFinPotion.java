package theAquaLance.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.powers.DrawPower;
import theAquaLance.AquaLanceMod;

import static theAquaLance.AquaLanceMod.AQUALANCE_EYE_COLOR;
import static theAquaLance.util.Wiz.*;

public class SharkFinPotion extends AbstractEasyPotion {
    public static final String POTION_ID = AquaLanceMod.makeID("SharkFinPotion");
    private static final int DRAW_AMT = 1;

    public SharkFinPotion() {
        super(POTION_ID, PotionRarity.RARE, PotionSize.M, PotionColor.SMOKE, false, false);
        PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
        String[] potionDesc = potionStrings.DESCRIPTIONS;
        if (potency == 1)
            description = potionDesc[0] + potency + potionDesc[1];
        else
            description = potionDesc[0] + potency + potionDesc[2];
        tips.clear();
        tips.add(new PowerTip(name, description));
        labOutlineColor = AQUALANCE_EYE_COLOR;
    }

    public void use(AbstractCreature target) {
        applyToSelf(new DrawPower(adp(), potency));
    }

    public int getPotency(int ascensionLevel) { return DRAW_AMT; }
}