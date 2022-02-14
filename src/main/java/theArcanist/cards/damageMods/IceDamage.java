package theArcanist.cards.damageMods;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theArcanist.ArcanistMod;
import theArcanist.powers.FrostbitePower;
import theArcanist.util.Wiz;

import java.util.ArrayList;

@AutoAdd.Ignore
public class IceDamage extends AbstractDamageModifier {
    public static final String ID = ArcanistMod.makeID("IceDamage");
    public final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public TooltipInfo iceTooltip;
    public TooltipInfo iceTooltip2;
    private boolean visibleTips = true;

    public IceDamage() {
        iceTooltip = null;
        iceTooltip2 = null;
    }

    public IceDamage(boolean visibleTips) {
        this();
        this.visibleTips = visibleTips;
    }

    @Override
    public void onDamageModifiedByBlock(DamageInfo info, int unblockedAmount, int blockedAmount,
                                        AbstractCreature target) {
        int totalDamage = unblockedAmount + blockedAmount;
        int frostbite = totalDamage/3;
        if (frostbite > 0)
            Wiz.applyToEnemyTop(target, new FrostbitePower(target, frostbite));
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        if (!visibleTips)
            return new ArrayList<TooltipInfo>();
        if (iceTooltip == null)
            iceTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);
        if (iceTooltip2 == null)
            iceTooltip2 = new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[1], cardStrings.EXTENDED_DESCRIPTION[2]);
        return new ArrayList<TooltipInfo>() { { add(iceTooltip); add(iceTooltip2); } };
    }

    //Overriding this to true tells us that this damage mod is considered part of the card and not just something added on to the card later.
    //If you ever add a damage modifier during the initialization of a card, it should be inherent.
    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        IceDamage output = new IceDamage();
        output.iceTooltip = this.iceTooltip;
        output.iceTooltip2 = this.iceTooltip2;
        return new IceDamage();
    }
}
