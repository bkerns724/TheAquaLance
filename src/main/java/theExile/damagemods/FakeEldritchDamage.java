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
import theExile.icons.Eldritch;

import java.util.ArrayList;

@AutoAdd.Ignore
public class FakeEldritchDamage extends AbstractDamageModifier {
    public static final String FAKE_ID = ExileMod.makeID(EldritchDamage.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(FAKE_ID);
    public TooltipInfo eldritchTooltip;
    public TooltipInfo eldritchTooltip2;
    private boolean visibleTips = true;

    private int blockedAmount = 0;

    public FakeEldritchDamage() {
        eldritchTooltip = null;
        eldritchTooltip2 = null;
    }

    public FakeEldritchDamage(boolean visTips) {
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
        if (eldritchTooltip == null)
            eldritchTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);
        if (eldritchTooltip2 == null)
            eldritchTooltip2 = new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[1], cardStrings.EXTENDED_DESCRIPTION[2]);
        return new ArrayList<TooltipInfo>() { { add(eldritchTooltip); add(eldritchTooltip2);} };
    }

    public static ArrayList<PowerTip> getPowerTips() {
        ArrayList<PowerTip> list = new ArrayList<>();
        list.add(new PowerTip(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]));
        list.add(new PowerTip(cardStrings.EXTENDED_DESCRIPTION[1], cardStrings.EXTENDED_DESCRIPTION[2]));
        return list;
    }

    //Overriding this to true tells us that this damage mod is considered part of the card and not just something added on to the card later.
    //If you ever add a damage modifier during the initialization of a card, it should be inherent.
    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        FakeEldritchDamage output = new FakeEldritchDamage(visibleTips);
        output.eldritchTooltip = this.eldritchTooltip;
        output.eldritchTooltip2 = this.eldritchTooltip2;
        return output;
    }

    @Override
    public AbstractCustomIcon getAccompanyingIcon() {
        return new Eldritch();
    }
}
