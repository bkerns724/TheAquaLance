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
import theExile.icons.Lightning;
import theExile.powers.ChargePower;

import java.util.ArrayList;

import static theExile.util.Wiz.adp;

@AutoAdd.Ignore
public class DeathLightningDamage extends AbstractDamageModifier {
    public static final String ID = ExileMod.makeID(DeathLightningDamage.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public TooltipInfo lightningTooltip;
    public TooltipInfo lightningTooltip2;
    private boolean visibleTips = true;

    public DeathLightningDamage() {
        lightningTooltip = null;
        lightningTooltip2 = null;
    }

    public DeathLightningDamage(boolean visTips) {
        this();
        visibleTips = visTips;
    }

    @Override
    public boolean shouldPushIconToCard(AbstractCard card) {
        return true;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        AbstractPower powCharge = adp().getPower(ChargePower.POWER_ID);
        if (powCharge == null)
            return damage;

        return damage + powCharge.amount;
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        if (!visibleTips)
            return new ArrayList<>();
        if (lightningTooltip == null)
            lightningTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);
        if (lightningTooltip2 == null)
            lightningTooltip = new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[1], cardStrings.EXTENDED_DESCRIPTION[2]);

        return new ArrayList<TooltipInfo>() { { add(lightningTooltip); } };
    }

    public static ArrayList<PowerTip> getPowerTips() {
        ArrayList<PowerTip> list = new ArrayList<>();
        list.add(new PowerTip(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]));
        return list;
    }

    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        DeathLightningDamage output = new DeathLightningDamage(visibleTips);
        output.lightningTooltip = this.lightningTooltip;
        output.lightningTooltip2 = this.lightningTooltip2;
        return output;
    }

    @Override
    public AbstractCustomIcon getAccompanyingIcon() {
        return new Lightning();
    }
}
