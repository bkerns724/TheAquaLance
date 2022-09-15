package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import javassist.CtBehavior;
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
}
