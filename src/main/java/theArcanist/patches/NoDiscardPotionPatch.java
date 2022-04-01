package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.EntropicBrew;
import com.megacrit.cardcrawl.potions.FruitJuice;
import theArcanist.events.MarketActOne;

@SpirePatch2(
        clz = AbstractPotion.class,
        method = "canDiscard"
)
@SpirePatch2(
        clz = FruitJuice.class,
        method = "canUse"
)
@SpirePatch2(
        clz = EntropicBrew.class,
        method = "canUse"
)
public class NoDiscardPotionPatch {
    @SpirePrefixPatch
    public static SpireReturn<Boolean> Prefix() {
        if ((AbstractDungeon.getCurrRoom()).event != null && (AbstractDungeon.getCurrRoom()).event instanceof MarketActOne)
            return SpireReturn.Return(false);
        return SpireReturn.Continue();
    }
}
