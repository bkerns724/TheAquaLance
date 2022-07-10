package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.EntropicBrew;
import com.megacrit.cardcrawl.potions.FruitJuice;

public class NoDiscardPotionPatch {
    @SpirePatch2(
            clz = AbstractPotion.class,
            method = "canDiscard"
    )
    public static class InterceptDiscard {
        @SpirePrefixPatch
        public static SpireReturn<Boolean> Prefix(AbstractPotion __instance) {
            if (PotionDiscardField.eventReserved.get(__instance))
                return SpireReturn.Return(false);
            return SpireReturn.Continue();
        }
    }

    @SpirePatch2(
            clz = EntropicBrew.class,
            method = "canUse"
    )
    public static class InterceptEntropicUse {
        @SpirePrefixPatch
        public static SpireReturn<Boolean> Prefix(EntropicBrew __instance) {
            if (PotionDiscardField.eventReserved.get(__instance))
                return SpireReturn.Return(false);
            return SpireReturn.Continue();
        }
    }

    @SpirePatch2(
            clz = FruitJuice.class,
            method = "canUse"
    )
    public static class InterceptFruitUse {
        @SpirePrefixPatch
        public static SpireReturn<Boolean> Prefix(FruitJuice __instance) {
            if (PotionDiscardField.eventReserved.get(__instance))
                return SpireReturn.Return(false);
            return SpireReturn.Continue();
        }
    }

    @SpirePatch2(
            clz = AbstractPotion.class,
            method = "canUse"
    )
    public static class InterceptUse {
        @SpirePrefixPatch
        public static SpireReturn<Boolean> Prefix(AbstractPotion __instance) {
            if (PotionDiscardField.eventReserved.get(__instance))
                return SpireReturn.Return(false);
            return SpireReturn.Continue();
        }
    }

    @SpirePatch2(
            clz = AbstractPotion.class,
            method = SpirePatch.CLASS
    )
    public static class PotionDiscardField {
        public static SpireField<Boolean> eventReserved = new SpireField<>(() -> false);
    }
}