package theAquaLance.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PainfulStabsPower;
import javassist.CtBehavior;
import javassist.expr.Expr;
import theAquaLance.actions.HobbleAction;
import theAquaLance.powers.HobbledPower;

import static theAquaLance.util.Wiz.*;

public class HobblePatch {

    @SpirePatch(clz= GameActionManager.class, method=SpirePatch.CLASS)
    private static class ActionManagerField {
        public static SpireField<AbstractMonster> actionMon = new SpireField<>(() -> null);
    }

    @SpirePatch(clz= AbstractGameAction.class, method=SpirePatch.CLASS)
    private static class ActionField {
        public static SpireField<AbstractMonster> actionMon = new SpireField<>(() -> null);
    }

    @SpirePatch(clz = GameActionManager.class, method = "getNextAction")
    public static class SetFieldPatch {
        @SpireInsertPatch(locator = Locator.class, localvars = {"m"})
        public static void Insert(GameActionManager __instance, @ByRef AbstractMonster[] m) {
            ActionManagerField.actionMon.set(__instance, m[0]);
        }
        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractMonster.class, "takeTurn");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }

    @SpirePatch(clz = GameActionManager.class, method = "getNextAction")
    public static class ResetFieldPatch {
        @SpireInsertPatch(locator = Locator.class, localvars = {"m"})
        public static void Insert(GameActionManager __instance, @ByRef AbstractMonster[] m) {
            ActionManagerField.actionMon.set(__instance, null);
        }
        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractMonster.class, "applyTurnPowers");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }

    @SpirePatch(clz = GameActionManager.class, method = "addToTop")
    @SpirePatch(clz = GameActionManager.class, method = "addToBottom")
    public static class DontMakeTempCardPls {
        @SpirePrefixPatch()
        public static void MissTarget(GameActionManager __instance, AbstractGameAction action) {
            AbstractMonster m = ActionManagerField.actionMon.get(__instance);
            if ((action instanceof MakeTempCardInDiscardAction
                    || action instanceof MakeTempCardInDiscardAndDeckAction
                    || action instanceof MakeTempCardInDrawPileAction
                    || action instanceof MakeTempCardInHandAction)
                    && m != null) {
                ActionField.actionMon.set(action, m);
            }
        }
    }

    @SpirePatch(clz = MakeTempCardInDiscardAction.class, method = "update")
    public static class HobbleStopCardDiscardPatch {
        @SpirePrefixPatch()
        public static SpireReturn HobbleStop(MakeTempCardInDiscardAction __instance) {
            AbstractMonster m = ActionField.actionMon.get(__instance);
            if (m != null && m.hasPower(HobbledPower.POWER_ID)) {
                AbstractPower hobPow = m.getPower(HobbledPower.POWER_ID);
                if (hobPow.amount > 0) {
                    __instance.isDone = true;
                    att(new HobbleAction(m));
                    return SpireReturn.Return(null);
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = MakeTempCardInDrawPileAction.class, method = "update")
    public static class HobbleStopCardDrawPatch {
        @SpirePrefixPatch()
        public static SpireReturn HobbleStop(MakeTempCardInDrawPileAction __instance) {
            AbstractMonster m = ActionField.actionMon.get(__instance);
            if (m != null && m.hasPower(HobbledPower.POWER_ID)) {
                AbstractPower hobPow = m.getPower(HobbledPower.POWER_ID);
                if (hobPow.amount > 0) {
                    __instance.isDone = true;
                    att(new HobbleAction(m));
                    return SpireReturn.Return(null);
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = MakeTempCardInDiscardAndDeckAction.class, method = "update")
    public static class HobbleStopCardDisDrawPatch {
        @SpirePrefixPatch()
        public static SpireReturn HobbleStop(MakeTempCardInDiscardAndDeckAction __instance) {
            AbstractMonster m = ActionField.actionMon.get(__instance);
            if (m != null && m.hasPower(HobbledPower.POWER_ID)) {
                AbstractPower hobPow = m.getPower(HobbledPower.POWER_ID);
                if (hobPow.amount > 0) {
                    __instance.isDone = true;
                    att(new HobbleAction(m));
                    return SpireReturn.Return(null);
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = MakeTempCardInHandAction.class, method = "update")
    public static class HobbleStopCardHandPatch {
        @SpirePrefixPatch()
        public static SpireReturn HobbleStop(MakeTempCardInHandAction __instance) {
            AbstractMonster m = ActionField.actionMon.get(__instance);
            if (m != null && m.hasPower(HobbledPower.POWER_ID)) {
                AbstractPower hobPow = m.getPower(HobbledPower.POWER_ID);
                if (hobPow.amount > 0) {
                    __instance.isDone = true;
                    att(new HobbleAction(m));
                    return SpireReturn.Return(null);
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = PainfulStabsPower.class, method = "onInflictDamage")
    public static class HobbleStopCardStabPatch {
        @SpireInsertPatch(locator = Locator.class)
        public static SpireReturn HobbleStop(PainfulStabsPower __instance) {
            if (__instance.owner instanceof AbstractMonster) {
                MakeTempCardInDiscardAction disAction = new MakeTempCardInDiscardAction(new Wound(), 1);
                ActionField.actionMon.set(disAction, (AbstractMonster)__instance.owner );
                atb(disAction);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(PainfulStabsPower.class, "addToBot");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }


}