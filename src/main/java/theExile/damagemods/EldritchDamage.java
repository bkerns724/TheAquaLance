package theExile.damagemods;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import theExile.ExileMod;
import theExile.actions.MyAddTempHPAction;
import theExile.icons.Eldritch;
import theExile.powers.ElementalProwessPower;
import theExile.relics.VoidBracelet;

import java.util.ArrayList;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

@AutoAdd.Ignore
public class EldritchDamage extends AbstractDamageModifier {
    public static final String ID = ExileMod.makeID(EldritchDamage.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public TooltipInfo darkTooltip;
    public TooltipInfo darkTooltip2;
    private boolean visibleTips = true;

    private int blockedAmount = 0;

    public EldritchDamage() {
        darkTooltip = null;
        darkTooltip2 = null;
    }

    public EldritchDamage(boolean visTips) {
        this();
        visibleTips = visTips;
    }

    @Override
    public boolean shouldPushIconToCard(AbstractCard card) {
        return true;
    }

    @Override
    public void onDamageModifiedByBlock(DamageInfo info, int unblockedAmount, int blockedAmount, AbstractCreature target) {
        this.blockedAmount = blockedAmount;
    }

    @Override
    public void onLastDamageTakenUpdate(DamageInfo info, int lastDamageTaken, int overkillAmount, AbstractCreature target) {
        int finalDamage = blockedAmount + lastDamageTaken;
        if (adp().hasRelic(VoidBracelet.ID))
            finalDamage *= 2;
        int tempHP = finalDamage / 3;
        ElementalProwessPower power = (ElementalProwessPower) adp().getPower(ElementalProwessPower.POWER_ID);
        if (power != null)
            tempHP += power.amount;
        if (tempHP > 0)
            att(new MyAddTempHPAction(adp(), adp(), tempHP));
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        if (!visibleTips)
            return new ArrayList<>();
        if (darkTooltip == null)
            darkTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);
        if (darkTooltip2 == null)
            darkTooltip2 = new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[1], cardStrings.EXTENDED_DESCRIPTION[2]);
        return new ArrayList<TooltipInfo>() { { add(darkTooltip); add(darkTooltip2);} };
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
        EldritchDamage output = new EldritchDamage(visibleTips);
        output.darkTooltip = this.darkTooltip;
        output.darkTooltip2 = this.darkTooltip2;
        return output;
    }

    @Override
    public AbstractCustomIcon getAccompanyingIcon() {
        return new Eldritch();
    }
}
