package theExile.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import javassist.CtBehavior;
import theExile.ExileMod;
import theExile.orbs.CrazyPanda;

import static theExile.util.Wiz.adp;

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
            for (CrazyPanda panda : ExileMod.pandaList) {
                AbstractOrbIsInPlayerRender.isPlayerRender.set(panda, false);
                panda.render(sb);
            }
        }
    }

    @SpirePatch2(
            clz = EvokeWithoutRemovingOrbAction.class,
            method = "update"
    )
    public static class NotBoringPlease {
        @SpirePrefixPatch
        public static void Prefix(EvokeWithoutRemovingOrbAction __instance) {
            int amount = ReflectionHacks.getPrivate(__instance, EvokeWithoutRemovingOrbAction.class, "orbCount");
            if (amount > 1)
                return;
            if (adp() != null && adp().orbs.size() > 0) {
                AbstractOrb o = adp().orbs.get(0);
                if (o instanceof CrazyPanda)
                    ReflectionHacks.setPrivate(__instance, AbstractGameAction.class, "isDone", true);
            }
        }
    }
}
