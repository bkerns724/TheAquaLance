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
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.ExileMod;
import theExile.icons.Force;
import theExile.powers.ForceChargePower;

import java.util.ArrayList;

import static theExile.util.Wiz.adp;

@AutoAdd.Ignore
public class ForceDamage extends AbstractDamageModifier {
    public static final String ID = ExileMod.makeID(ForceDamage.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private TooltipInfo forceTooltip;
    private TooltipInfo forceTooltip2;
    private boolean visibleTips = true;

    public ForceDamage() {
        forceTooltip = null;
        forceTooltip2 = null;
    }

    public ForceDamage(boolean visTips) {
        this();
        visibleTips = visTips;
    }

    @Override
    public boolean shouldPushIconToCard(AbstractCard card) {
        return true;
    }


    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        AbstractPower powCharge = adp().getPower(ForceChargePower.POWER_ID);
        if (powCharge == null)
            return damage;

        float bonus = powCharge.amount * ForceChargePower.BONUS;

        return damage * (1f + bonus);
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
        ForceDamage output = new ForceDamage(visibleTips);
        output.forceTooltip = this.forceTooltip;
        output.forceTooltip2 = this.forceTooltip2;
        return output;
    }

    @Override
    public AbstractCustomIcon getAccompanyingIcon() {
        return new Force();
    }
}
