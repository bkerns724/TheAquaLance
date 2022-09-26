package theExile.patches;


import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.cards.AbstractExileCard;

public class OnPickupPatch {
    @SpirePatch2(
            clz = ShowCardAndObtainEffect.class,
            method = "update"
    )
    public static class ShowCardPatch {
        @SpirePostfixPatch
        public static void Postfix(ShowCardAndObtainEffect __instance) {
            if (__instance.isDone) {
                AbstractCard card = ReflectionHacks.getPrivate(__instance, ShowCardAndObtainEffect.class, "card");
                if (card instanceof AbstractExileCard)
                    ((AbstractExileCard) card).onPickup();
            }
        }
    }

    @SpirePatch2(
            clz = FastCardObtainEffect.class,
            method = "update"
    )
    public static class FastObtainPatch {
        @SpirePostfixPatch
        public static void Insert(FastCardObtainEffect __instance) {
            if (__instance.isDone) {
                AbstractCard card = ReflectionHacks.getPrivate(__instance, FastCardObtainEffect.class, "card");
                if (card instanceof AbstractExileCard)
                    ((AbstractExileCard) card).onPickup();
            }
        }
    }
}
