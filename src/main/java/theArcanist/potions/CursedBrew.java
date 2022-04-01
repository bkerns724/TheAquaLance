package theArcanist.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theArcanist.powers.JinxPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.applyToEnemy;

public class CursedBrew extends AbstractArcanistPotion {
    public static final String POTION_ID = makeID(CursedBrew.class.getSimpleName());
    private static final String KEYWORD_NAME = makeID("Jinx");
    public static final int DEFAULT_POTENCY = 3;
    public static final boolean IS_THROWN = true;
    public static final boolean TARGET_REQUIRED = true;
    public static final PotionRarity RARITY = PotionRarity.COMMON;
    public static final PotionSize SIZE = PotionSize.EYE;

    public CursedBrew() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
        tips.add(new PowerTip(BaseMod.getKeywordTitle(KEYWORD_NAME),
                BaseMod.getKeywordDescription(KEYWORD_NAME)));
    }

    public void use(AbstractCreature target) {
        applyToEnemy(target, new JinxPower(target, potency));
    }
}