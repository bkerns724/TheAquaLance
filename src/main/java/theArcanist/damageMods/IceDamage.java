package theArcanist.damageMods;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theArcanist.ArcanistMod;
import theArcanist.Icons.Ice;
import theArcanist.patches.DamageModsIDPatch;
import theArcanist.powers.FrostbitePower;
import theArcanist.relics.BlueMarbles;
import theArcanist.relics.ManaPurifier;

import java.util.ArrayList;

import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public class IceDamage extends AbstractDamageModifier {
    public static final String ID = ArcanistMod.makeID(IceDamage.class.getSimpleName());
    public final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public TooltipInfo iceTooltip;
    public TooltipInfo iceTooltip2;
    private boolean visibleTips = true;

    public IceDamage() {
        iceTooltip = null;
        iceTooltip2 = null;
        DamageModsIDPatch.ID.set(this, ID);
    }

    public IceDamage(boolean visibleTips) {
        this();
        this.visibleTips = visibleTips;
    }

    @Override
    public boolean shouldPushIconToCard(AbstractCard card) {
        return true;
    }

    @Override
    public void onDamageModifiedByBlock(DamageInfo info, int unblockedAmount, int blockedAmount,
                                        AbstractCreature target) {
        if (adp() == null || adp().hasRelic(ManaPurifier.ID))
            return;;
        int totalDamage = unblockedAmount + blockedAmount;
        if (adp().hasRelic(BlueMarbles.ID))
            totalDamage *= 2;
        int frostbite = totalDamage/3;
        if (frostbite > 0) {
            applyToEnemyTop(target, new FrostbitePower(target, frostbite));
            att(new WaitAction(0.1f));
        }
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

    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        IceDamage output = new IceDamage();
        output.iceTooltip = this.iceTooltip;
        output.iceTooltip2 = this.iceTooltip2;
        return output;
    }

    @Override
    public AbstractCustomIcon getAccompanyingIcon() {
        return new Ice();
    }
}
