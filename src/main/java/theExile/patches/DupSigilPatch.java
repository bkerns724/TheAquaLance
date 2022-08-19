package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import javassist.CtBehavior;
import theExile.cards.AbstractExileCard;

// Explosive Potions should have always been THORNS damage
public class DupSigilPatch {
    @SpirePatch2(
            clz = DuplicationPower.class,
            method = "onUseCard"
    )
    public static class DupedSigilIsDiscarded {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"tmp"}
        )
        public static void Insert(DuplicationPower __instance, AbstractCard tmp) {
            if (tmp instanceof AbstractExileCard && ((AbstractExileCard) tmp).sigil)
                ((AbstractExileCard) tmp).beingDiscarded = true;
        }
        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(CardGroup.class, "addToBottom");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}
