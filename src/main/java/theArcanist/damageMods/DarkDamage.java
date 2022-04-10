package theArcanist.damageMods;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theArcanist.ArcanistMod;
import theArcanist.Icons.Dark;
import theArcanist.actions.MyAddTempHPAction;
import theArcanist.patches.DamageModsIDPatch;
import theArcanist.powers.EldritchStaffPower;
import theArcanist.relics.BlueMarbles;
import theArcanist.relics.ManaPurifier;
import theArcanist.relics.DarkFunnel;

import java.util.ArrayList;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;
import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public class DarkDamage extends AbstractDamageModifier {
    public static final String ID = ArcanistMod.makeID(DarkDamage.class.getSimpleName());
    public CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public TooltipInfo darkTooltip;
    public TooltipInfo darkTooltip2;
    private boolean visibleTips = true;

    public DarkDamage() {
        darkTooltip = null;
        darkTooltip2 = null;
        DamageModsIDPatch.ID.set(this, ID);
    }

    public DarkDamage(boolean visTips) {
        this();
        visibleTips = visTips;
    }

    @Override
    public boolean shouldPushIconToCard(AbstractCard card) {
        return true;
    }

    @Override
    public void onDamageModifiedByBlock(DamageInfo info, int unblockedAmount, int blockedAmount, AbstractCreature targetHit) {
        if (adp() == null || adp().hasRelic(ManaPurifier.ID))
            return;
        int tempHP;
        if (adp().hasRelic(BlueMarbles.ID))
            tempHP = GetTriangleNumberRootFloor(unblockedAmount*2);
        else
            tempHP = GetTriangleNumberRootFloor(unblockedAmount);
        if (tempHP > 0) {
            if (adp().hasRelic(DarkFunnel.ID))
                tempHP += 2;
            if (adp().hasPower(EldritchStaffPower.POWER_ID)) {
                int mult = adp().getPower(EldritchStaffPower.POWER_ID).amount + 1;
                att(new MyAddTempHPAction(adp(), adp(), tempHP*mult));
            }
            else
                att(new MyAddTempHPAction(adp(), adp(), tempHP));
        }
    }

    private static int GetTriangleNumberRootFloor(int n) {
        double x = -0.5f + sqrt(0.25f + 2*n);
        return (int) floor(x);
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        if (!visibleTips)
            return new ArrayList<TooltipInfo>();
        if (darkTooltip == null)
            darkTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);
        if (darkTooltip2 == null)
            darkTooltip2 = new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[1], cardStrings.EXTENDED_DESCRIPTION[2]);
        return new ArrayList<TooltipInfo>() { { add(darkTooltip); add(darkTooltip2);} };
    }

    //Overriding this to true tells us that this damage mod is considered part of the card and not just something added on to the card later.
    //If you ever add a damage modifier during the initialization of a card, it should be inherent.
    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        DarkDamage output = new DarkDamage();
        output.darkTooltip = this.darkTooltip;
        output.darkTooltip2 = this.darkTooltip2;
        return output;
    }

    @Override
    public AbstractCustomIcon getAccompanyingIcon() {
        return new Dark();
    }
}
