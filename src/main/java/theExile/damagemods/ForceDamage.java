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
import theExile.icons.Force;
import theExile.powers.CrushedPower;
import theExile.relics.BlueMarbles;

import java.util.ArrayList;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToEnemyTop;

@AutoAdd.Ignore
public class ForceDamage extends AbstractDamageModifier {
    public static final String ID = ExileMod.makeID(ForceDamage.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public TooltipInfo forceTooltip;
    public TooltipInfo forceTooltip2;
    private boolean visibleTips = true;

    private int blockedAmount = 0;

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
    public void onDamageModifiedByBlock(DamageInfo info, int unblockedAmount, int blockedAmount, AbstractCreature target) {
        this.blockedAmount = blockedAmount;
    }

    @Override
    public void onLastDamageTakenUpdate(DamageInfo info, int lastDamageTaken, int overkillAmount, AbstractCreature target) {
        int finalDamage = lastDamageTaken + blockedAmount;
        if (adp().hasRelic(BlueMarbles.ID))
            finalDamage *= BlueMarbles.INCREASE;
        int crushed = finalDamage / 5;
        if (crushed > 0)
            applyToEnemyTop(target, new CrushedPower(target, crushed));
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        if (!visibleTips)
            return new ArrayList<TooltipInfo>();
        if (forceTooltip == null)
            forceTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);
        if (forceTooltip2 == null)
            forceTooltip2 = new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[1], cardStrings.EXTENDED_DESCRIPTION[2]);

        return new ArrayList<TooltipInfo>() { { add(forceTooltip); add(forceTooltip2); } };
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
