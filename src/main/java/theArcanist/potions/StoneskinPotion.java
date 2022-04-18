package theArcanist.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.powers.StoneskinPower;

import static com.megacrit.cardcrawl.potions.AbstractPotion.PotionRarity.COMMON;
import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.applyToSelf;

public class StoneskinPotion extends AbstractArcanistPotion {
    public static final String POTION_ID = makeID(StoneskinPotion.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 4;
    public static final PotionRarity RARITY = COMMON;
    public static final PotionSize SIZE = PotionSize.HEART;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public StoneskinPotion() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    public void use(AbstractCreature target) {
        applyToSelf(new StoneskinPower(adp(), potency));
    }
}