package theExile.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.powers.FrostbitePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class LiquidNitrogen extends AbstractExilePotion {
    public static final String POTION_ID = makeID(LiquidNitrogen.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 6;
    public static final PotionRarity RARITY = PotionRarity.COMMON;
    public static final PotionSize SIZE = PotionSize.M;
    public static final boolean IS_THROWN = true;
    public static final boolean TARGET_REQUIRED = true;

    public LiquidNitrogen() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() {
        keywordStrings.add(makeID("Frostbite"));
    }

    public void use(AbstractCreature target) {
        applyToEnemy(target, new FrostbitePower(target, potency));
    }
}