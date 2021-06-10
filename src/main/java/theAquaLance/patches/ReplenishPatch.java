package theAquaLance.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;

import static theAquaLance.util.Wiz.*;

public class ReplenishPatch {
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "draw",
            paramtypez = {int.class}
    )
    public static class DrawInsertPatch {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars ="c"
        )
        public static void Insert(AbstractPlayer __instance, int numCards, AbstractCard c) {
            // draws another card if card drawn had replenish
            if (AbstractCardPatch.AbstractCardField.replenish.get(c))
                att(new DrawCardAction(1));
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "triggerWhenDrawn");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}