package theArcanist.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageNumberEffect;
import javassist.*;

public class ColoredDamagePatch {
    public static Color currentColor = null;
    public static FadeSpeed currentSpeed = FadeSpeed.NONE;

    public enum FadeSpeed {
        NONE,
        SLOW,
        MODERATE,
        FAST
    }

    @SpirePatch2 (
            clz = DamageAction.class,
            method = SpirePatch.CLASS
    )
    public static class DamageActionColorField {
        public static SpireField<Color> damageColor = new SpireField<>(() -> null);
        public static SpireField<FadeSpeed> fadeSpeed = new SpireField<>(() -> null);
    }

    @SpirePatch2(
            clz = DamageAction.class,
            method = "update"
    )
    public static class DamageActionUpdate {
        @SpireInsertPatch (
                locator = Locator.class
        )
        public static void Insert(DamageAction __instance) {
            ColoredDamagePatch.currentColor = DamageActionColorField.damageColor.get(__instance);
            ColoredDamagePatch.currentSpeed = DamageActionColorField.fadeSpeed.get(__instance);
        }
        private static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "damage");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }

    @SpirePatch2(
            clz = DamageNumberEffect.class,
            method = SpirePatch.CLASS
    )
    public static class DamageNumberColorField {
        public static SpireField<Color> damageColor = new SpireField<>(() -> null);
        public static SpireField<FadeSpeed> fadeSpeed = new SpireField<>(() -> null);
    }

    @SpirePatch2(
            clz = DamageNumberEffect.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class MakeColor {
        @SpirePostfixPatch
        public static void Postfix(DamageNumberEffect __instance) {
            if (currentColor != null) {
                ReflectionHacks.setPrivate(__instance, AbstractGameEffect.class, "color", currentColor.cpy());
                DamageNumberColorField.damageColor.set(__instance, currentColor.cpy());
            }
            else {
                Color color = ReflectionHacks.getPrivate(__instance, AbstractGameEffect.class, "color");
                DamageNumberColorField.damageColor.set(__instance, color.cpy());
            }

            if (currentSpeed != null)
                DamageNumberColorField.fadeSpeed.set(__instance, currentSpeed);
            else
                DamageNumberColorField.fadeSpeed.set(__instance, FadeSpeed.FAST);
        }
    }

    @SpirePatch2(
            clz = DamageNumberEffect.class,
            method = "update"
    )
    public static class MakeColor2 {
        @SpirePostfixPatch
        public static void Postfix(DamageNumberEffect __instance) {
            Color color = DamageNumberColorField.damageColor.get(__instance);
            FadeSpeed speed = DamageNumberColorField.fadeSpeed.get(__instance);
            float timeMult = 0;

            if (speed == FadeSpeed.FAST)
                timeMult = 4.0f;
            else if (speed == FadeSpeed.MODERATE)
                timeMult = 2.0f;
            else if (speed == FadeSpeed.SLOW)
                timeMult = 1.0f;

            float delta = Gdx.graphics.getDeltaTime() * timeMult;
            if (speed != FadeSpeed.NONE) {
                if (color.r < 1.0F)
                    color.r += delta;
                if (color.g < 1.0F)
                    color.g += delta;
                if (color.b < 1.0F)
                    color.b += delta;
                color.clamp();
            }

            // Don't copy completely, don't overwrite alpha
            Color color2 = ReflectionHacks.getPrivate(__instance, AbstractGameEffect.class, "color");
            color2.r = color.r;
            color2.g = color.g;
            color2.b = color.b;
        }
    }
}
