package theExile.damagemods;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import theExile.ExileMod;
import theExile.icons.Lightning;

import java.util.ArrayList;

@AutoAdd.Ignore
public class FakeLightningDamage extends AbstractDamageModifier {
    public static final String FAKE_ID = ExileMod.makeID(DeathLightningDamage.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(FAKE_ID);
    public TooltipInfo lightningTooltip;
    private boolean visibleTips = true;

    public FakeLightningDamage() {
        lightningTooltip = null;
    }

    public FakeLightningDamage(boolean visTips) {
        this();
        visibleTips = visTips;
    }

    @Override
    public boolean shouldPushIconToCard(AbstractCard card) {
        return true;
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        if (!visibleTips)
            return new ArrayList<>();
        if (lightningTooltip == null)
            lightningTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);

        return new ArrayList<TooltipInfo>() { { add(lightningTooltip); } };
    }

    public static ArrayList<PowerTip> getPowerTips() {
        ArrayList<PowerTip> list = new ArrayList<>();
        list.add(new PowerTip(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]));
        return list;
    }

    //Overriding this to true tells us that this damage mod is considered part of the card and not just something added on to the card later.
    //If you ever add a damage modifier during the initialization of a card, it should be inherent.
    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        FakeLightningDamage output = new FakeLightningDamage(visibleTips);
        output.lightningTooltip = this.lightningTooltip;
        return output;
    }

    @Override
    public AbstractCustomIcon getAccompanyingIcon() {
        return new Lightning();
    }
}
