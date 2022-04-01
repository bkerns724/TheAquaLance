package theArcanist.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theArcanist.actions.IgnorePainAction;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.atb;

public class NumbingPotion extends AbstractArcanistPotion {
    public static final String POTION_ID = makeID(NumbingPotion.class.getSimpleName());
    private static final String KEYWORD_NAME = "status";
    public static final int DEFAULT_POTENCY = 5;
    public static final PotionRarity RARITY = PotionRarity.RARE;
    public static final PotionSize SIZE = PotionSize.FAIRY;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public NumbingPotion() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
        tips.add(new PowerTip(BaseMod.getKeywordTitle(KEYWORD_NAME),
                BaseMod.getKeywordDescription(KEYWORD_NAME)));
    }

    public void use(AbstractCreature target) {
        for (int i = 0; i < potency; i++)
            atb(new IgnorePainAction());
    }
}