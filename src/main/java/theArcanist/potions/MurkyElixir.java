package theArcanist.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.powers.ShadowcloakPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.applyToSelf;

public class MurkyElixir extends AbstractArcanistPotion {
    public static final String POTION_ID = makeID(MurkyElixir.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 2;
    public static final PotionRarity RARITY = PotionRarity.COMMON;
    public static final PotionSize SIZE = PotionSize.M;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public MurkyElixir() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() {
        keywordStrings.add(makeID("Shadowcloak"));
    }

    public void use(AbstractCreature target) {
        applyToSelf(new ShadowcloakPower(adp(), potency));
    }
}