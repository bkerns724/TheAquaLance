package theExile.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.powerInterfaces.HealthBarRenderPowerPatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import javassist.CtBehavior;
import theExile.powers.DecayPower;

public class HealthBarRenderPatch {

    @SpirePatch2(
            clz = AbstractCreature.class,
            method = "renderRedHealthBar"
    )
    public static class AdjustVanillaPoison {
        @SpireInsertPatch (
                locator = Locator.class,
                localvars = {"poisonAmt"}
        )
        public static void Insert(AbstractCreature __instance, @ByRef int[] poisonAmt) {
            DecayPower decayPow = (DecayPower) __instance.getPower(DecayPower.POWER_ID);
            if (decayPow != null)
                poisonAmt[0] += decayPow.amount;
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "hasPower");
                int[] x = LineFinder.findAllInOrder(ctBehavior, matcher);
                return new int[]{x[1]};
            }
        }
    }

    @SpirePatch(
            clz = HealthBarRenderPowerPatch.RenderPowerHealthBar.class,
            method = "Insert"
    )
    public static class AdjustStslibPoison {
        @SpireInsertPatch (
                locator = Locator.class,
                localvars = {"poisonAmt"}
        )
        public static void MyInsert(AbstractCreature __instance, SpriteBatch sb, float x, float y, float targetHealthBarWidth,
                                    float HEALTH_BAR_HEIGHT, float HEALTH_BAR_OFFSET_Y, @ByRef int[] poisonAmt) {
            DecayPower decayPow = (DecayPower) __instance.getPower(DecayPower.POWER_ID);
            if (decayPow != null)
                poisonAmt[0] += decayPow.amount;
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "hasPower");
                int[] x = LineFinder.findAllInOrder(ctBehavior, matcher);
                return new int[]{x[1]};
            }
        }
    }
}