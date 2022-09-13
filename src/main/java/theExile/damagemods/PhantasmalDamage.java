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
import theExile.icons.Phantasmal;

import java.util.ArrayList;

@AutoAdd.Ignore
public class PhantasmalDamage extends AbstractDamageModifier {
    public static final String ID = ExileMod.makeID(PhantasmalDamage.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private TooltipInfo forceTooltip;
    private TooltipInfo forceTooltip2;
    private boolean visibleTips = true;

    public PhantasmalDamage() {
        forceTooltip = null;
        forceTooltip2 = null;
    }

    public PhantasmalDamage(boolean visTips) {
        this();
        visibleTips = visTips;
    }

    @Override
    public boolean shouldPushIconToCard(AbstractCard card) {
        return true;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        // code is in ForceChargePower so the damage preview shows up from ApplyPowers();
        return damage;
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        if (!visibleTips)
            return new ArrayList<>();
        if (forceTooltip == null)
            forceTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);
        if (forceTooltip2 == null)
            forceTooltip2 = new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[1], cardStrings.EXTENDED_DESCRIPTION[2]);

        return new ArrayList<TooltipInfo>() { { add(forceTooltip); add(forceTooltip2);} };
    }

    public static ArrayList<PowerTip> getPowerTips() {
        ArrayList<PowerTip> list = new ArrayList<>();
        list.add(new PowerTip(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]));
        list.add(new PowerTip(cardStrings.EXTENDED_DESCRIPTION[1], cardStrings.EXTENDED_DESCRIPTION[2]));
        return list;
    }

    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        PhantasmalDamage output = new PhantasmalDamage(visibleTips);
        output.forceTooltip = this.forceTooltip;
        output.forceTooltip2 = this.forceTooltip2;
        return output;
    }

    @Override
    public AbstractCustomIcon getAccompanyingIcon() {
        return new Phantasmal();
    }
}
