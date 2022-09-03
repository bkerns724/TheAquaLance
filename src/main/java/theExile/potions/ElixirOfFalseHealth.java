package theExile.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.actions.MyAddTempHPAction;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class ElixirOfFalseHealth extends AbstractExilePotion {
    public static final String POTION_ID = makeID(ElixirOfFalseHealth.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 20;
    public static final PotionRarity RARITY = PotionRarity.RARE;
    public static final PotionSize SIZE = PotionSize.HEART;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public ElixirOfFalseHealth() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() {
        if (keywordStrings == null)
            keywordStrings = new ArrayList<>();
        keywordStrings.add("temporary_hp");
    }

    public void use(AbstractCreature target) {
        atb(new MyAddTempHPAction(adp(), adp(), potency));
    }
}