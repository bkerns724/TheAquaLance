package theExile.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;
import theExile.powers.ToxicOilPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class ToxicOil extends AbstractExilePotion {
    public static final String POTION_ID = makeID(ToxicOil.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 2;
    public static final PotionRarity RARITY = ExileMod.Enums.EVENT;
    public static final PotionSize SIZE = PotionSize.T;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public ToxicOil() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() {
        keywordStrings.add(makeID("Decay"));
    }

    public void use(AbstractCreature target) {
        applyToSelf(new ToxicOilPower(potency));
    }
}