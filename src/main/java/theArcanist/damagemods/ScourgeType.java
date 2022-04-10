package theArcanist.damagemods;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theArcanist.ArcanistMod;
import theArcanist.icons.Scourge;
import theArcanist.patches.DamageModsIDPatch;

import java.util.ArrayList;

@AutoAdd.Ignore
public class ScourgeType extends AbstractDamageModifier {
    public static final String ID = ArcanistMod.makeID(ScourgeType.class.getSimpleName());
    public CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public TooltipInfo scourgeTooltip;
    private boolean visibleTips = true;

    public ScourgeType() {
        scourgeTooltip = null;
        DamageModsIDPatch.ID.set(this, ID);
    }

    public ScourgeType(boolean visTips) {
        this();
        visibleTips = visTips;
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        if (!visibleTips)
            return new ArrayList<>();
        if (scourgeTooltip == null)
            scourgeTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);
        ArrayList<TooltipInfo> list = new ArrayList<>();
        list.add(scourgeTooltip);
        return list;
    }

    //Overriding this to true tells us that this damage mod is considered part of the card and not just something added on to the card later.
    //If you ever add a damage modifier during the initialization of a card, it should be inherent.
    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        ScourgeType output = new ScourgeType();
        output.scourgeTooltip = this.scourgeTooltip;
        return output;
    }

    @Override
    public AbstractCustomIcon getAccompanyingIcon() {
        return new Scourge();
    }
}
