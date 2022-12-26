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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theExile.ExileMod;
import theExile.icons.Force;
import theExile.relics.Beastiary;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;
import static theExile.util.Wiz.adp;

@AutoAdd.Ignore
public class ForceDamage extends AbstractDamageModifier {
    public static final String ID = ExileMod.makeID(ForceDamage.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private TooltipInfo forceTooltip;
    private boolean visibleTips = true;
    private static final float BONUS = 0.5f;

    public ForceDamage() {
        forceTooltip = null;
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
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        if (target == null)
            // HOW DID THAT HAPPEN?
            return damage;
        if (type == NORMAL) {
            float mult = 1f;
            if (adp().hasRelic(Beastiary.ID))
                mult += Beastiary.BONUS;
            else
                mult += BONUS;

            return target.hasPower(VulnerablePower.POWER_ID) ? damage * mult : damage;
        }
        return damage;
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        if (!visibleTips)
            return new ArrayList<>();
        if (forceTooltip == null)
            forceTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);

        return new ArrayList<TooltipInfo>() { { add(forceTooltip);} };
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
        ForceDamage output = new ForceDamage(visibleTips);
        output.forceTooltip = this.forceTooltip;
        return output;
    }

    @Override
    public AbstractCustomIcon getAccompanyingIcon() {
        return new Force();
    }
}
