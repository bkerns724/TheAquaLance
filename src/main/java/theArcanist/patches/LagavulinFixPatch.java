package theArcanist.patches;

import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.monsters.exordium.Lagavulin;
import javassist.CtBehavior;

@SpirePatch(
        clz = Lagavulin.class,
        method = "damage"
)
public class LagavulinFixPatch {
    @SpireInsertPatch(locator = Locator.class)
    public static SpireReturn<Void> LagavulinNotInvis(Lagavulin __instance) {
        if (__instance.state.getCurrent(0).getAnimation().getName().equals("Coming_out"))
            return SpireReturn.Return();
        return SpireReturn.Continue();
    }
    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher matcher = new Matcher.MethodCallMatcher(AnimationState.class, "setAnimation");
            return LineFinder.findInOrder(ctBehavior, matcher);
        }
    }
}