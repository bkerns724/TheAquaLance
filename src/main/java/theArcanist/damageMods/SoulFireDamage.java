package theArcanist.damageMods;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theArcanist.ArcanistMod;
import theArcanist.Icons.SoulFire;
import theArcanist.patches.DamageModsIDPatch;
import theArcanist.relics.ManaPurifier;

import java.util.ArrayList;

import static theArcanist.util.Wiz.adp;

@AutoAdd.Ignore
public class SoulFireDamage extends AbstractDamageModifier {
    public static final String ID = ArcanistMod.makeID(SoulFireDamage.class.getSimpleName());
    public final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public TooltipInfo soulFireTooltip;
    private boolean visibleTips = true;

    public SoulFireDamage() {
        soulFireTooltip = null;
        DamageModsIDPatch.ID.set(this, ID);
    }

    public SoulFireDamage(boolean visTips) {
        this();
        visibleTips = visTips;
    }

    @Override
    public boolean ignoresBlock(AbstractCreature dummy) {
        return adp() == null || !adp().hasRelic(ManaPurifier.ID);
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        if (!visibleTips)
            return new ArrayList<TooltipInfo>();
        if (soulFireTooltip == null)
            soulFireTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);

        return new ArrayList<TooltipInfo>() { { add(soulFireTooltip); } };
    }

    //Overriding this to true tells us that this damage mod is considered part of the card and not just something added on to the card later.
    //If you ever add a damage modifier during the initialization of a card, it should be inherent.
    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        SoulFireDamage output = new SoulFireDamage();
        output.soulFireTooltip = this.soulFireTooltip;
        return output;
    }

    @Override
    public AbstractCustomIcon getAccompanyingIcon() {
        return new SoulFire();
    }
}
