package theArcanist.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.ArcanistMod;
import theArcanist.powers.SteelhidePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.applyToSelf;

public class SteelhidePotion extends AbstractArcanistPotion {
    public static final String POTION_ID = makeID(SteelhidePotion.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 3;
    public static final PotionRarity RARITY = ArcanistMod.Enums.EVENT;
    public static final PotionSize SIZE = PotionSize.H;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public SteelhidePotion() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    public void use(AbstractCreature target) {
        applyToSelf(new SteelhidePower(adp(), potency));
    }
}