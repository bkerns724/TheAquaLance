package theArcanist.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theArcanist.actions.MyAddTempHPAction;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.atb;

public class ElixirOfFalseHealth extends AbstractArcanistPotion {
    public static final String POTION_ID = makeID(ElixirOfFalseHealth.class.getSimpleName());
    public static final String KEYWORD_NAME = "temporary_hp";
    public static final int DEFAULT_POTENCY = 35;
    public static final PotionRarity RARITY = PotionRarity.RARE;
    public static final PotionSize SIZE = PotionSize.HEART;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public ElixirOfFalseHealth() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
        tips.add(new PowerTip(BaseMod.getKeywordTitle(KEYWORD_NAME),
                BaseMod.getKeywordDescription(KEYWORD_NAME)));
    }

    public void use(AbstractCreature target) {
        int amount = (int) (adp().maxHealth*35f/100f);
        atb(new MyAddTempHPAction(adp(), adp(), amount));
    }
}