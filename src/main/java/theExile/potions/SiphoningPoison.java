package theExile.potions;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import theExile.ExileMod;
import theExile.damagemods.DeathLightningDamage;
import theExile.damagemods.EldritchDamage;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class SiphoningPoison extends AbstractExilePotion {
    public static final String POTION_ID = makeID(SiphoningPoison.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 24;
    public static final PotionRarity RARITY = ExileMod.Enums.EVENT;
    public static final PotionSize SIZE = PotionSize.HEART;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;
    private static final CardStrings lightningStrings = CardCrawlGame.languagePack.getCardStrings(DeathLightningDamage.ID);

    public SiphoningPoison() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    @Override
    public void setKeywordStrings() {
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(lightningStrings.DESCRIPTION, lightningStrings.EXTENDED_DESCRIPTION[0]));
    }

    public void use(AbstractCreature target) {
        DamageModContainer container = new DamageModContainer(this, new EldritchDamage());
        DamageAllEnemiesAction action = new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(potency, true),
                DamageInfo.DamageType.THORNS, ExileMod.Enums.ELDRITCH_M);
        BindingHelper.bindAction(container, action);
        ColoredDamagePatch.DamageActionColorField.damageColor.set(action, Color.BLACK.cpy());
        ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(action, ColoredDamagePatch.FadeSpeed.SLOW);
        atb(action);
    }
}