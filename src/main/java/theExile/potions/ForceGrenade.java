package theExile.potions;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.relics.SacredBark;
import theExile.ExileMod;
import theExile.damagemods.ForceDamage;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class ForceGrenade extends AbstractExilePotion {
    public static final String POTION_ID = makeID(ForceGrenade.class.getSimpleName());
    private static final CardStrings forceStrings = CardCrawlGame.languagePack.getCardStrings(ForceDamage.ID);
    public static final int DEFAULT_POTENCY = 1;
    public static final PotionRarity RARITY = PotionRarity.UNCOMMON;
    public static final PotionSize SIZE = PotionSize.BOTTLE;
    public static final boolean IS_THROWN = true;
    public static final boolean TARGET_REQUIRED = false;
    private final DamageModContainer container;
    private static final String crushKey = "Crushed";

    public ForceGrenade() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
        container = new DamageModContainer(this, new ForceDamage());
    }

    @Override
    public void setKeywordStrings() {
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        potionStrings = CardCrawlGame.languagePack.getPotionString(ID);

        description = potionStrings.DESCRIPTIONS[0].replace("!P!", "#b" + potency);

        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(BaseMod.getKeywordTitle(crushKey), BaseMod.getKeywordDescription(crushKey)));
        tips.add(new PowerTip(forceStrings.DESCRIPTION, forceStrings.EXTENDED_DESCRIPTION[0]));

    }

    public void use(AbstractCreature target) {
        atb(BindingHelper.makeAction(container, new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(potency,
                true), DamageInfo.DamageType.NORMAL,
                adp().hasRelic(SacredBark.ID) ? ExileMod.Enums.FORCE_M : ExileMod.Enums.FORCE)));
    }
}