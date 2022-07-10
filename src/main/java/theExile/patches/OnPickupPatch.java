package theExile.patches;


import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.cards.AbstractExileCard;

public class OnPickupPatch {
    @SpirePatch2(
            clz = ShowCardAndObtainEffect.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCard.class, float.class, float.class, boolean.class}
    )
    public static class ShowCardPatch {
        @SpirePostfixPatch
        public static void Postfix(ShowCardAndObtainEffect __instance) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, ShowCardAndObtainEffect.class, "card");
            if (card instanceof AbstractExileCard)
                ((AbstractExileCard) card).onPickup();
        }
    }

    @SpirePatch2(
            clz = FastCardObtainEffect.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCard.class, float.class, float.class}
    )
    public static class FastObtainPatch {
        @SpirePostfixPatch
        public static void Insert(FastCardObtainEffect __instance) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, FastCardObtainEffect.class, "card");
            if (card instanceof AbstractExileCard)
                ((AbstractExileCard) card).onPickup();
        }
    }
}
