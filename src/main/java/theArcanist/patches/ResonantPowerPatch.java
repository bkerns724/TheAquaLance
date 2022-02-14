package theArcanist.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.PowerBuffEffect;
import javassist.CtBehavior;
import theArcanist.ArcanistMod;
import theArcanist.powers.ResonatingPower;

public class ResonantPowerPatch {
    private static final String RESONATING_MSG = " Intensifies";

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class AbstractCardField {
        public static SpireField<Boolean> resonance = new SpireField<>(() -> false);
    }

    @SpirePatch2(
            clz = ApplyPowerAction.class,
            method = "update"
    )
    public static class StackResonantPower {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"p", "hasBuffAlready"}
        )
        public static SpireReturn InsertPatch(ApplyPowerAction __instance, AbstractPower p, boolean hasBuffAlready) {
            AbstractPower pow = ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class,"powerToApply");
            if (pow == null) {
                ArcanistMod.logger.info("Null power to apply");
                return SpireReturn.Continue();
            }
            if (p == null) {
                ArcanistMod.logger.info("Null local var p");
                return SpireReturn.Continue();
            }
            if (pow instanceof ResonatingPower && p instanceof ResonatingPower) {
                ((ResonatingPower) p).stackPower((ResonatingPower) pow);
                AbstractDungeon.effectList.add(new PowerBuffEffect(__instance.target.hb.cX - __instance.target.animX,
                        __instance.target.hb.cY + __instance.target.hb.height / 2.0F, pow.name + RESONATING_MSG));

                p.updateDescription();
                hasBuffAlready = true;
                AbstractDungeon.onModifyPower();
                ReflectionHacks.RMethod method = ReflectionHacks.privateMethod(AbstractGameAction.class, "tickDuration");
                method.invoke(__instance);
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
        private static class Locator extends SpireInsertLocator {
            private Locator() {}
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractPower.class, "stackPower");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }

    @SpirePatch2(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class AnimateResonantCard {

        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn InsertPatch (UseCardAction __instance) {
            AbstractCard targetCard = ReflectionHacks.getPrivate(__instance, UseCardAction.class, "targetCard");
            if (AbstractCardField.resonance.get(targetCard))
            {
                AbstractDungeon.actionManager.addToTop(new ShowCardAction(targetCard));
                if (Settings.FAST_MODE)
                    AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
                else
                    AbstractDungeon.actionManager.addToTop(new WaitAction(0.7F));

                AbstractDungeon.player.hand.empower(targetCard);
                ReflectionHacks.setPrivate(__instance, AbstractGameAction.class, "isDone", true);
                AbstractDungeon.player.hand.applyPowers();
                AbstractDungeon.player.hand.glowCheck();
                AbstractDungeon.player.cardInUse = null;
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
        private static class Locator extends SpireInsertLocator {
            private Locator() {}
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "type");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}
