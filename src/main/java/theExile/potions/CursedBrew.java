package theExile.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.powers.JinxPower;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class CursedBrew extends AbstractExilePotion {
    public static final String POTION_ID = makeID(CursedBrew.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 3;
    public static final boolean IS_THROWN = true;
    public static final boolean TARGET_REQUIRED = true;
    public static final PotionRarity RARITY = PotionRarity.COMMON;
    public static final PotionSize SIZE = PotionSize.EYE;

    public CursedBrew() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() {
        if (keywordStrings == null)
            keywordStrings = new ArrayList<>();
        keywordStrings.add(makeID("Jinx"));
    }

    public void use(AbstractCreature target) {
        applyToEnemy(target, new JinxPower(target, potency));
    }
}