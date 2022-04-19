package theArcanist.damagemods;

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
import theArcanist.ArcanistMod;
import theArcanist.icons.SoulFire;
import theArcanist.patches.DamageModsIDPatch;
import theArcanist.powers.KindlingPower;

import java.util.ArrayList;

@AutoAdd.Ignore
public class SoulFireDamage extends AbstractDamageModifier {
    public static final String ID = ArcanistMod.makeID(SoulFireDamage.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
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
    public boolean shouldPushIconToCard(AbstractCard card) {
        return true;
    }

    @Override
    public boolean ignoresBlock(AbstractCreature dummy) {
        return true;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        AbstractPower pow = target.getPower(KindlingPower.POWER_ID);
        if (pow != null)
            damage *= (1.0f + pow.amount/100.0f);
        return damage;
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        if (!visibleTips)
            return new ArrayList<>();
        if (soulFireTooltip == null)
            soulFireTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);

        return new ArrayList<TooltipInfo>() { { add(soulFireTooltip); } };
    }

    public static ArrayList<PowerTip> getPowerTips() {
        ArrayList<PowerTip> list = new ArrayList<>();
        list.add(new PowerTip(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]));
        return list;
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
