package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.TheLibrary;
import javassist.CtBehavior;
import theArcanist.TheArcanist;

public class LibraryEventPatch {
    @SpirePatch2(
            clz = TheLibrary.class,
            method = "buttonEffect"
    )
    public static class UniqueCardInserter {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = "group"
        )
        public static SpireReturn insertPatch(TheLibrary __instance, int buttonPressed, @ByRef CardGroup[] group) {
            if (AbstractDungeon.player.chosenClass != TheArcanist.Enums.THE_ARCANIST)
                return SpireReturn.Continue();
            int index = AbstractDungeon.cardRng.random(0, 19);
            AbstractCard card = TheArcanist.getCardForLibrary();
            group[0].group.remove(index);
            group[0].group.add(index, card);
            return SpireReturn.Continue();
        }
        private static class Locator extends SpireInsertLocator {
            private Locator() {}
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(CardGroup.class, "group");
                int[] lines = LineFinder.findAllInOrder(ctBehavior, matcher);
                return new int[] {lines[1]};
            }
        }
    }
}
