package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import javassist.CtBehavior;
import theArcanist.cards.AbstractArcanistCard;

public class ScryPatch {
    @SpirePatch2(
            clz = ScryAction.class,
            method = "update"
    )
    public static class ScryDiscardHook {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = "c"
        )
        public static void Insert(ScryAction __instance, AbstractCard c) {
            if (c instanceof AbstractArcanistCard && DiscardTriggerField.triggerDiscard.get(__instance))
                ((AbstractArcanistCard)c).triggerOnManualDiscard();
        }
        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(CardGroup.class, "moveToDiscardPile");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }

    @SpirePatch2(
            clz = ScryAction.class,
            method = "<class>"
    )
    public static class DiscardTriggerField {
        public static SpireField<Boolean> triggerDiscard = new SpireField(() -> false);
    }
}
