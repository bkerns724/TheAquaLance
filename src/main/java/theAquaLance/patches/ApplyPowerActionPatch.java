package theAquaLance.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import theAquaLance.cards.AbstractEmbedCard;
import theAquaLance.powers.EmbedPower;
import theAquaLance.powers.FrostbitePower;
import theAquaLance.relics.UnmeltingIce;

import java.util.Arrays;

import static theAquaLance.util.Wiz.*;

public class ApplyPowerActionPatch {
    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = "update"
    )
    public static class ApplyPowerActionUpdatePatch {
        @SpirePrefixPatch
        public static SpireReturn Prefix(ApplyPowerAction __instance) {
            float dur = ReflectionHacks.getPrivate(__instance, AbstractGameAction.class, "duration");
            float startingDur = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "startingDuration");
            AbstractPower pow = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
            if (dur == startingDur && pow.ID.equals(EmbedPower.POWER_ID)) {
                if (__instance.target == null || __instance.target.isDeadOrEscaped()) {
                    ((EmbedPower) pow).popAll();
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
            return SpireReturn.Continue();
        }
        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasRelic");
                int[] Lines = LineFinder.findAllInOrder(ctMethodToPatch, matcher);
                return Arrays.copyOfRange(Lines, 1, 2);
            }
        }
    }

    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = { AbstractCreature.class, AbstractCreature.class, AbstractPower.class, int.class,
                    boolean.class, AbstractGameAction.AttackEffect.class }
    )
    public static class ApplyPowerActionConstructorPatch {
        @SpireInsertPatch(
                locator = ApplyPowerActionConstructorPatch.Locator.class
        )
        public static SpireReturn Insert(ApplyPowerAction __instance) {
            AbstractPower pow = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
            if (adp().hasRelic(UnmeltingIce.ID) && __instance.source != null && __instance.source.isPlayer &&
                    pow.ID.equals(FrostbitePower.POWER_ID)) {
                adp().getRelic(UnmeltingIce.ID).flash();
                ++pow.amount;
                ++__instance.amount;
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