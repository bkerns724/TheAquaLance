package theAquaLance.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import javassist.CtBehavior;
import theAquaLance.cards.AbstractEmbedCard;
import theAquaLance.powers.EmbedPower;
import theAquaLance.powers.HobbledPower;
import theAquaLance.relics.RuneOfLivingWater;

import static theAquaLance.util.Wiz.*;

public class ApplyPowerActionPatch {
    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = "update",
            paramtypez = {}
    )

    public static class ApplyPowerActionUpdatePatch {
        public static SpireReturn Prefix(ApplyPowerAction __instance) {
            float dur = ReflectionHacks.getPrivate(__instance, AbstractGameAction.class, "duration");
            float startingDur = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "startingDuration");
            AbstractPower pow = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
            if (dur == startingDur && pow.ID.equals(EmbedPower.POWER_ID)) {
                if (__instance.target == null || __instance.target.isDeadOrEscaped()) {
                    ((EmbedPower) pow).unEmbedAll();
                    __instance.isDone = true;
                    return SpireReturn.Return(null);
                }
            }
            return SpireReturn.Continue();
        }

        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn Insert(ApplyPowerAction __instance) {
            AbstractPower pow = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
            if (pow.ID.equals(EmbedPower.POWER_ID) && __instance.target.hasPower(EmbedPower.POWER_ID)) {
                EmbedPower ePow = (EmbedPower) __instance.target.getPower(EmbedPower.POWER_ID);
                AbstractEmbedCard card = ((EmbedPower)pow).cards.get(0);
                ePow.addCard(card);
                ePow.flash();
                AbstractDungeon.onModifyPower();
                __instance.isDone = true;
                return SpireReturn.Return(null);
            }

            AbstractPower power = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
            if (AbstractDungeon.player.hasRelic(RuneOfLivingWater.ID) && __instance.source != null
                    && __instance.source.isPlayer && power.ID.equals(HobbledPower.POWER_ID)
                    && !__instance.target.hasPower(ArtifactPower.POWER_ID)) {
                AbstractDungeon.player.getRelic(RuneOfLivingWater.ID).onTrigger(__instance.target);
            }
            return SpireReturn.Continue();
        }
        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasRelic");
                return LineFinder.findInOrder(ctMethodToPatch, matcher);
            }
        }
    }
}