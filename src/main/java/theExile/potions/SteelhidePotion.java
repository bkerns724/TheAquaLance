package theExile.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;
import theExile.powers.SteelhidePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class SteelhidePotion extends AbstractExilePotion {
    public static final String POTION_ID = makeID(SteelhidePotion.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 3;
    public static final PotionRarity RARITY = ExileMod.Enums.EVENT;
    public static final PotionSize SIZE = PotionSize.H;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public SteelhidePotion() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() { }

    public void use(AbstractCreature target) {
        applyToSelf(new SteelhidePower(adp(), potency));
    }
}