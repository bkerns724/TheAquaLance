package theExile.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.actions.ResonantDiscoveryAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class VibratingFlask extends AbstractExilePotion {
    public static final String POTION_ID = makeID(VibratingFlask.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 1;
    public static final PotionRarity RARITY = PotionRarity.UNCOMMON;
    public static final PotionSize SIZE = PotionSize.CARD;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public VibratingFlask() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() {
        keywordStrings.add(makeID("Resonant"));
    }

    public void use(AbstractCreature target) {
        atb(new ResonantDiscoveryAction(potency));
    }
}