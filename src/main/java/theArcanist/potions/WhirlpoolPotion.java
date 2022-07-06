package theArcanist.potions;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.atb;
import static theArcanist.util.Wiz.discard;

public class WhirlpoolPotion extends AbstractArcanistPotion {
    public static final String POTION_ID = makeID(WhirlpoolPotion.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 3;
    public static final PotionRarity RARITY = PotionRarity.UNCOMMON;
    public static final PotionSize SIZE = PotionSize.T;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public WhirlpoolPotion() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() { }

    public void use(AbstractCreature target) {
        atb(new DrawCardAction(potency));
        discard(potency);
    }
}