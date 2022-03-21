package theArcanist.cards.damageMods;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theArcanist.ArcanistMod;
import theArcanist.powers.CrushedPower;
import theArcanist.relics.BlueMarbles;
import theArcanist.relics.ManaPurifier;

import java.util.ArrayList;

import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public class ForceDamage extends AbstractDamageModifier {
    public static final String ID = ArcanistMod.makeID(ForceDamage.class.getSimpleName());
    public final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public TooltipInfo forceTooltip;
    public TooltipInfo forceTooltip2;
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
    public void onDamageModifiedByBlock(DamageInfo info, int unblockedAmount, int blockedAmount,
                                        AbstractCreature target) {
        if (adp() == null || adp().hasRelic(ManaPurifier.ID))
            return;
        int totalDamage = unblockedAmount + blockedAmount;
        if (adp().hasRelic(BlueMarbles.ID))
            totalDamage *= 2;
        int crushed = totalDamage/6;
        if (crushed > 0) {
            applyToEnemyTop(target, new CrushedPower(target, crushed));
            att(new WaitAction(0.1f));
        }
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

    //Overriding this to true tells us that this damage mod is considered part of the card and not just something added on to the card later.
    //If you ever add a damage modifier during the initialization of a card, it should be inherent.
    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        ForceDamage output = new ForceDamage();
        output.forceTooltip = this.forceTooltip;
        output.forceTooltip2 = this.forceTooltip2;
        return new ForceDamage();
    }
}
