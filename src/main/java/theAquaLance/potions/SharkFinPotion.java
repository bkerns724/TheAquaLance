package theAquaLance.potions;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import theAquaLance.AquaLanceMod;

import static theAquaLance.AquaLanceMod.AQUALANCE_EYE_COLOR;
import static theAquaLance.util.Wiz.*;

public class SharkFinPotion extends AbstractEasyPotion {
    public static final String POTION_ID = AquaLanceMod.makeID("SharkFinPotion");
    private static final int FLOW_AMT = 3;

    public SharkFinPotion() {
        super(POTION_ID, PotionRarity.RARE, PotionSize.M, PotionColor.SMOKE, false, false);
        PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
        String[] potionDesc = potionStrings.DESCRIPTIONS;
        description = potionDesc[0] + potency + potionDesc[1] + potency + potionDesc[2];
        tips.clear();
        tips.add(new PowerTip(name, description));
        labOutlineColor = AQUALANCE_EYE_COLOR;
    }

    public void use(AbstractCreature target) {
        atb(new DrawCardAction(potency));
        atb(new DiscardAction(adp(), adp(), potency, false));
    }

    public int getPotency(int ascensionLevel) { return FLOW_AMT; }
}