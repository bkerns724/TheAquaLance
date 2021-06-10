package theAquaLance.patches;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnReceivePowerPatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import theAquaLance.cards.AbstractEmbedCard;
import theAquaLance.powers.EmbedPower;

public class NegatedPowerPatch {
    @SpirePatch(
            clz = OnReceivePowerPatch.class,
            method = "CheckPower"
    )
    public static class OnReceivePowerPatchPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractGameAction action, AbstractCreature target, AbstractCreature source, float[] duration, AbstractPower powerToApply) {
            if (powerToApply instanceof EmbedPower) {
                for (AbstractEmbedCard c : ((EmbedPower) powerToApply).cards) {
                    c.hitArtifact = true;
                    c.current_y = target.hb.cY;
                    c.current_x = target.hb.cX;
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(GameActionManager.class, "addToTop");
                return LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}