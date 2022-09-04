package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;
import theExile.cards.AbstractExileCard;

public class SigilPatch {
    @SpirePatch2(
            clz = GameActionManager.class,
            method = "getNextAction"
    )
    public static class SigilResetOnCantPlay {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = "c"
        )
        public static void Insert(AbstractCard c) {
            if (c instanceof AbstractExileCard && ((AbstractExileCard) c).sigil)
                ((AbstractExileCard) c).beingDiscarded = false;
        }
        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "dialogX");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}
