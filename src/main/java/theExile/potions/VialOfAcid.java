package theExile.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.powers.CorrodedPower;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class VialOfAcid extends AbstractExilePotion {
    public static final String POTION_ID = makeID(VialOfAcid.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 5;
    public static final PotionRarity RARITY = PotionRarity.UNCOMMON;
    public static final PotionSize SIZE = PotionSize.T;
    public static final boolean IS_THROWN = true;
    public static final boolean TARGET_REQUIRED = true;

    public VialOfAcid() {
        super(POTION_ID, RARITY, SIZE, PotionColor.GREEN,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() {
        if (keywordStrings == null)
            keywordStrings = new ArrayList<>();
        keywordStrings.add(makeID("Corrosion"));
    }

    public void use(AbstractCreature target) {
        applyToEnemy(target, new CorrodedPower(target, potency));
    }
}