package theExile.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.SacredBark;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.cards.BadLuck;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class UnicornBlood extends AbstractExilePotion {
    public static final String POTION_ID = makeID(UnicornBlood.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 15;
    public static final PotionRarity RARITY = PotionRarity.RARE;
    public static final PotionSize SIZE = PotionSize.BOTTLE;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public UnicornBlood() {
        super(POTION_ID, RARITY, SIZE, PotionColor.WHITE,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() {
        if (keywordStrings == null)
            keywordStrings = new ArrayList<>();
        keywordStrings.add("curse");
    }

    public void use(AbstractCreature target) {
        adp().increaseMaxHp(potency, true);
        if (!adp().hasRelic(SacredBark.ID)) {
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new BadLuck(),
                    Settings.WIDTH * 0.5f, Settings.HEIGHT * 0.5f));
        } else {
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new BadLuck(),
                    Settings.WIDTH * 0.32f, Settings.HEIGHT * 0.5f));
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new BadLuck(),
                    Settings.WIDTH * 0.68f, Settings.HEIGHT * 0.5f));
        }
    }
}