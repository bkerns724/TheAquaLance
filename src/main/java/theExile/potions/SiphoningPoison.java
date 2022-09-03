package theExile.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;
import theExile.powers.SiphonPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class SiphoningPoison extends AbstractExilePotion {
    public static final String POTION_ID = makeID(SiphoningPoison.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 3;
    public static final PotionRarity RARITY = ExileMod.Enums.EVENT;
    public static final PotionSize SIZE = PotionSize.T;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public SiphoningPoison() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() {
    }

    public void use(AbstractCreature target) {
        applyToSelf(new SiphonPower(potency));
    }
}