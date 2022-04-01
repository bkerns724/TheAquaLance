package theArcanist.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theArcanist.powers.CrushedPower;
import theArcanist.powers.FrostbitePower;
import theArcanist.powers.JinxPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.applyToEnemy;

public class LiquidCalamity extends AbstractArcanistPotion {
    public static final String POTION_ID = makeID(LiquidCalamity.class.getSimpleName());
    private static final String KEYWORD_NAME1 = makeID("Frostbite");
    private static final String KEYWORD_NAME2 = makeID("Crushed");
    private static final String KEYWORD_NAME3 = makeID("Jinx");
    public static final int DEFAULT_POTENCY = 1;
    public static final PotionRarity RARITY = PotionRarity.UNCOMMON;
    public static final PotionSize SIZE = PotionSize.BOTTLE;
    public static final boolean IS_THROWN = true;
    public static final boolean TARGET_REQUIRED = true;
    public static final int FROSTBITE_MULTIPLIER = 4;
    public static final int CRUSHED_MULTIPLIER = 2;
    public static final int JINX_MULTIPLIER = 2;

    public LiquidCalamity() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
        tips.add(new PowerTip(BaseMod.getKeywordTitle(KEYWORD_NAME1),
                BaseMod.getKeywordDescription(KEYWORD_NAME1)));
        tips.add(new PowerTip(BaseMod.getKeywordTitle(KEYWORD_NAME2),
                BaseMod.getKeywordDescription(KEYWORD_NAME2)));
        tips.add(new PowerTip(BaseMod.getKeywordTitle(KEYWORD_NAME3),
                BaseMod.getKeywordDescription(KEYWORD_NAME3)));
    }

    public void use(AbstractCreature target) {
        applyToEnemy(target, new FrostbitePower(target, FROSTBITE_MULTIPLIER*potency));
        applyToEnemy(target, new CrushedPower(target, CRUSHED_MULTIPLIER*potency));
        applyToEnemy(target, new JinxPower(target, JINX_MULTIPLIER*potency));
    }

    @Override
    public void setDescription() {
        description = potionStrings.DESCRIPTIONS[0].replace("!P1!", "#b" + potency*FROSTBITE_MULTIPLIER);
        description = description.replace("!P2!", "#b" + potency*CRUSHED_MULTIPLIER);
        description = description.replace("!P3!", "#b" + potency*JINX_MULTIPLIER);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
}