package theArcanist.patches;


import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import javassist.CtBehavior;
import theArcanist.cards.AbstractArcanistCard;

public class OnPickupPatch {
    @SpirePatch2(
            clz = ShowCardAndObtainEffect.class,
            method = "update"
    )
    public static class ShowCardPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(ShowCardAndObtainEffect __instance) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, ShowCardAndObtainEffect.class, "card");
            if (card instanceof AbstractArcanistCard)
                ((AbstractArcanistCard) card).onPickup();
        }

        public static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractCard.class, "shrink");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
    @SpirePatch2(
            clz = FastCardObtainEffect.class,
            method = "update"
    )
    public static class FastObtainPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(FastCardObtainEffect __instance) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, FastCardObtainEffect.class, "card");
            if (card instanceof AbstractArcanistCard)
                ((AbstractArcanistCard) card).onPickup();
        }

        public static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractCard.class, "shrink");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}
