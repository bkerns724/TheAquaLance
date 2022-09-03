package theExile.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.powers.StoneskinPower;

import static com.megacrit.cardcrawl.potions.AbstractPotion.PotionRarity.UNCOMMON;
import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class StoneskinPotion extends AbstractExilePotion {
    public static final String POTION_ID = makeID(StoneskinPotion.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 5;
    public static final PotionRarity RARITY = UNCOMMON;
    public static final PotionSize SIZE = PotionSize.SPHERE;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public StoneskinPotion() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() { }

    public void use(AbstractCreature target) {
        applyToSelf(new StoneskinPower(adp(), potency));
    }
}