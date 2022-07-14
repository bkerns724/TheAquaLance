package theExile.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.math.Vector2;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.Soul;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.EmpowerEffect;
import javassist.CtBehavior;
import theExile.cards.TwistedForm;
import theExile.powers.TwistedFormPower;

public class TwistedFormPatch {
    @SpirePatch2(
            clz = RemoveSpecificPowerAction.class,
            method = "update"
    )
    public static class DoNotRemoveTwistedForm {
        @SpirePrefixPatch
        public static SpireReturn prefix(RemoveSpecificPowerAction __instance) {
            AbstractPower pow = ReflectionHacks.getPrivate(__instance, RemoveSpecificPowerAction.class, "powerInstance");
            if (pow != null) {
                if (pow instanceof TwistedFormPower) {
                    __instance.isDone = true;
                    return SpireReturn.Return();
                }
                else
                    return SpireReturn.Continue();
            }
            String powString = ReflectionHacks.getPrivate(__instance, RemoveSpecificPowerAction.class, "powerToRemove");
            if (powString != null) {
                if (powString.equals(TwistedFormPower.POWER_ID)) {
                    __instance.isDone = true;
                    return SpireReturn.Return();
                }
                else
                    return SpireReturn.Continue();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch2(
            clz = ApplyPowerAction.class,
            method = "update"
    )
    public static class DoNotArtifactTwistedForm {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void insertBeforeArtifact(ApplyPowerAction __instance) {
            AbstractPower pow = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
            if (pow instanceof TwistedFormPower)
                pow.type = AbstractPower.PowerType.BUFF;
        }
        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher.FieldAccessMatcher matcher = new Matcher.FieldAccessMatcher(AbstractPower.class, "type");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }

        @SpireInsertPatch (
                locator = Locator2.class
        )
        public static void insertAfterArtifact(ApplyPowerAction __instance) {
            AbstractPower pow = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
            if (pow instanceof TwistedFormPower)
                pow.type = AbstractPower.PowerType.DEBUFF;
        }
        public static class Locator2 extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher.FieldAccessMatcher matcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "effectList");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }

        @SpirePostfixPatch
        public static void postfix(ApplyPowerAction __instance) {
            if (__instance.isDone) {
                AbstractPower pow = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
                if (pow instanceof TwistedFormPower)
                    ((TwistedFormPower) pow).addToList();
            }
        }
    }

    @SpirePatch2(
            clz = Soul.class,
            method = "empower"
    )
    public static class PowerOnEnemy {
        @SpirePostfixPatch
        public static void postfix(Soul __instance) {
            AbstractCard card = __instance.card;
            if (card instanceof TwistedForm) {
                AbstractMonster m = ((TwistedForm) card).targetMonsterForTwisted;
                Vector2 target = ReflectionHacks.getPrivate(__instance, Soul.class, "target");
                target.x = m.hb.cX;
                target.y = m.hb.y;
                ReflectionHacks.RMethod method = ReflectionHacks.privateMethod(Soul.class, "setSharedVariables");
                method.invoke(__instance);
            }
        }
    }

    @SpirePatch2(
            clz = Soul.class,
            method = "update"
    )
    public static class BubblesOnEnemy {
        @SpireInsertPatch (
                locator = Locator3.class
        )
        public static SpireReturn insert(Soul __instance) {
            AbstractCard card = __instance.card;
            if (card instanceof TwistedForm) {
                AbstractMonster m = ((TwistedForm) card).targetMonsterForTwisted;
                AbstractDungeon.effectList.add(new EmpowerEffect(m.hb.cX, m.hb.cY));
                __instance.isReadyForReuse = true;
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
        public static class Locator3 extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher.FieldAccessMatcher matcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "effectList");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }
}