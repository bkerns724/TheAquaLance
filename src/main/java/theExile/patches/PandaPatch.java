package theExile.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import javassist.CtBehavior;
import theExile.orbs.CrazyPanda;

public class PandaPatch {
    @SpirePatch (
            clz =AbstractOrb.class,
            method = SpirePatch.CLASS
    )
    public static class AbstractOrbIsInPlayerRender {
        public static SpireField<Boolean> isPlayerRender = new SpireField<Boolean>(() -> {return false;});
    }

    @SpirePatch2(
            clz = AbstractPlayer.class,
            method = "render"
    )
    public static class DoNotRenderOrbWithPlayer {
        @SpireInsertPatch (
                locator = Locator.class,
                localvars = {"o"}
        )
        public static void Insert(AbstractPlayer __instance, AbstractOrb o) {
            AbstractOrbIsInPlayerRender.isPlayerRender.set(o, true);
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractOrb.class, "render");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }

    @SpirePatch2(
            clz = AbstractPlayer.class,
            method = "renderPlayerBattleUi"
    )
    public static class RenderOrbAfterMonster {
        @SpirePrefixPatch
        public static void Prefix(AbstractPlayer __instance, SpriteBatch sb) {
            for (AbstractOrb o : __instance.orbs) {
                AbstractOrbIsInPlayerRender.isPlayerRender.set(o, false);
                if (o instanceof CrazyPanda)
                    o.render(sb);
            }
        }
    }

}
