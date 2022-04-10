package theArcanist.damageMods;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theArcanist.ArcanistMod;
import theArcanist.Icons.Ice;
import theArcanist.patches.DamageModsIDPatch;

import java.util.ArrayList;

@AutoAdd.Ignore
public class FakeIceDamage extends AbstractDamageModifier {
    public static final String ID = ArcanistMod.makeID(FakeIceDamage.class.getSimpleName());
    public final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(IceDamage.ID);
    public TooltipInfo iceTooltip;
    public TooltipInfo iceTooltip2;
    private boolean visibleTips = true;

    public FakeIceDamage() {
        iceTooltip = null;
        iceTooltip2 = null;
        DamageModsIDPatch.ID.set(this, ID);
    }

    public FakeIceDamage(boolean visibleTips) {
        this();
        this.visibleTips = visibleTips;
    }

    @Override
    public boolean shouldPushIconToCard(AbstractCard card) {
        return true;
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        if (!visibleTips)
            return new ArrayList<>();
        if (iceTooltip == null)
            iceTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);
        if (iceTooltip2 == null)
            iceTooltip2 = new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[1], cardStrings.EXTENDED_DESCRIPTION[2]);
        return new ArrayList<TooltipInfo>() { { add(iceTooltip); add(iceTooltip2); } };
    }

    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        FakeIceDamage output = new FakeIceDamage();
        output.iceTooltip = this.iceTooltip;
        output.iceTooltip2 = this.iceTooltip2;
        return output;
    }

    @Override
    public AbstractCustomIcon getAccompanyingIcon() {
        return new Ice();
    }
}
