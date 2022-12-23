package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;

public class MonsterDamageHook {
    @SpirePatch2(
            clz = AbstractMonster.class,
            method = "damage"
    )
    public static class HookForHpLoss {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"damageAmount"}
        )
        public static void Insert(AbstractMonster __instance, @ByRef int[] damageAmount) {
            for (AbstractPower pow : __instance.powers)
                damageAmount[0] = pow.onLoseHp(damageAmount[0]);
        }

        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(CardCrawlGame.class, "overkill");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}
