package theArcanist.cards.damageMods;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import theArcanist.ArcanistMod;

import java.util.ArrayList;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;
import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public class DarkDamage extends AbstractDamageModifier {
    public static final String ID = ArcanistMod.makeID("DarkDamage");
    public final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public TooltipInfo darkTooltip;
    public TooltipInfo darkTooltip2;

    public DarkDamage() {
        darkTooltip = null;
        darkTooltip2 = null;
    }

    @Override
    public void onDamageModifiedByBlock(DamageInfo info, int unblockedAmount, int blockedAmount, AbstractCreature targetHit) {
        AbstractPlayer p = AbstractDungeon.player;
        int tempHP = GetTriangleNumberRootFloor(unblockedAmount);
        if (tempHP > 0)
            att(new AddTemporaryHPAction(p, p, tempHP));
    }

    private static int GetTriangleNumberRootFloor(int n) {
        double x = -0.5f + sqrt(0.25f + 2*n);
        return (int) floor(x);
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
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
        return new DarkDamage();
    }
}
