package theArcanist.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theArcanist.cards.BadLuck;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;

public class UnicornBlood extends AbstractArcanistPotion {
    public static final String POTION_ID = makeID(UnicornBlood.class.getSimpleName());
    private static final String KEYWORD_NAME = "status";
    public static final int DEFAULT_POTENCY = 15;
    public static final PotionRarity RARITY = PotionRarity.RARE;
    public static final PotionSize SIZE = PotionSize.BOTTLE;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public UnicornBlood() {
        super(POTION_ID, RARITY, SIZE, PotionColor.WHITE,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
        cardToPreview = new BadLuck();
        tips.add(new PowerTip(BaseMod.getKeywordTitle(KEYWORD_NAME),
                BaseMod.getKeywordDescription(KEYWORD_NAME)));
    }

    public void use(AbstractCreature target) {
        adp().increaseMaxHp(potency, true);
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new BadLuck(),
                Settings.WIDTH*0.5f, Settings.HEIGHT*0.5f));
    }
}