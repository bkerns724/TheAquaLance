package theExile.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import javassist.CtBehavior;
import theExile.ExileMod;
import theExile.orbs.OnAttackOrb;

import static theExile.util.Wiz.adp;

public class BeesPatch {
    @SpirePatch2(
            clz = AbstractMonster.class,
            method = "damage"
    )
    public static class OprahWinfrey {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractMonster __instance, DamageInfo info) {
            for (AbstractOrb orb : adp().orbs) {
                if (orb instanceof OnAttackOrb)
                    ((OnAttackOrb) orb).onAttack(__instance, info);
            }
        }
        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "currentBlock");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }

    @SpirePatch2(
            clz = DamageAction.class,
            method = "update"
    )
    public static class NoExtraBees {
        @SpirePrefixPatch
        public static SpireReturn Prefix (DamageAction __instance) {
            AbstractCreature target = ReflectionHacks.getPrivate(__instance, AbstractGameAction.class, "target");
            if (__instance.attackEffect == ExileMod.Enums.BEE && target != null && target.isDeadOrEscaped()) {
                __instance.isDone = true;
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
