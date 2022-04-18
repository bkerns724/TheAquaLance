package theArcanist.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import theArcanist.cards.AbstractArcanistCard;

import static theArcanist.ArcanistMod.getColor;

public class CardBgPatch {
    private static Color oldColor = new Color();
    private static Color oldScvColor = new Color();

    @SpirePatch2(clz = AbstractCard.class, method = "renderCardBg")
    public static class RegularPatch {
        @SpirePrefixPatch
        public static void Prefix(AbstractCard __instance) {
            if (!(__instance instanceof AbstractArcanistCard))
                return;
            oldColor = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
            Color newColor = getColor();
            newColor.a = oldColor.a;
            ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", newColor);
        }

        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance) {
            if (__instance instanceof AbstractArcanistCard)
                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", oldColor);
        }
    }

    @SpirePatch2(clz = SingleCardViewPopup.class, method = "renderCardBack")
    public static class ScvPatch {
        @SpirePrefixPatch
        public static void Prefix(SingleCardViewPopup __instance, SpriteBatch sb) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (!(card instanceof AbstractArcanistCard))
                return;
            oldScvColor = sb.getColor();
            Color newColor = getColor();
            newColor.a = oldScvColor.a;
            sb.setColor(newColor);
        }

        @SpirePostfixPatch
        public static void Postfix(SingleCardViewPopup __instance, SpriteBatch sb) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (card instanceof AbstractArcanistCard)
                sb.setColor(oldScvColor);
        }
    }
}
