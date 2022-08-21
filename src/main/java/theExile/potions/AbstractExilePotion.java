package theExile.potions;

import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.relics.SacredBark;
import theExile.ExileMod;

import java.util.ArrayList;

import static theExile.util.Wiz.adp;

public abstract class
AbstractExilePotion extends CustomPotion {
    protected PotionStrings potionStrings;
    protected int defaultPotency;
    protected ArrayList<String> keywordStrings;

    protected static final Color FLAVOR_BOX_COLOR = new Color(0.45f, 0, 0.65f, 1.0f);
    protected static final Color FLAVOR_TEXT_COLOR = new Color(1.0F, 0.9725F, 0.8745F, 1.0F);

    public AbstractExilePotion(String potionID, PotionRarity rarity, PotionSize size, PotionColor color,
                               boolean isThrown, boolean targetRequired, int defaultPotency) {
        super(CardCrawlGame.languagePack.getPotionString(potionID).NAME, potionID, rarity, size, color);
        labOutlineColor = ExileMod.EXILE_EYE_COLOR;
        this.isThrown = isThrown;
        this.targetRequired = targetRequired;
        this.defaultPotency = defaultPotency;
        FlavorText.PotionFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.PotionFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        initializeData();
    }

    public abstract void setKeywordStrings();

    @Override
    public void initializeData() {
        potency = getPotency();
        potionStrings = CardCrawlGame.languagePack.getPotionString(ID);

        if (adp() != null && adp().hasRelic(SacredBark.ID) && potionStrings.DESCRIPTIONS.length > 1)
            description = potionStrings.DESCRIPTIONS[1].replace("!P!", "#b" + potency);
        else
            description = potionStrings.DESCRIPTIONS[0].replace("!P!", "#b" + potency);

        tips.clear();
        tips.add(new PowerTip(name, description));

        keywordStrings = new ArrayList<>();
        setKeywordStrings();

        for (String s : keywordStrings)
            tips.add(new PowerTip(BaseMod.getKeywordTitle(s), BaseMod.getKeywordDescription(s)));
    }

    public CustomPotion makeCopy() {
        try {
            return getClass().newInstance();
        } catch (IllegalAccessException | InstantiationException var2) {
            RuntimeException e = new RuntimeException("BaseMod failed to auto-generate makeCopy for potion: " + ID);
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int getPotency(int i) {
        return defaultPotency;
    }
}