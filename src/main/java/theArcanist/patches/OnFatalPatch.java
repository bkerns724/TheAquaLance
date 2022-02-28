package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import javassist.CtBehavior;
import theArcanist.powers.OnFatalPower;

import static theArcanist.util.Wiz.*;

// Can't do things with actions if you use this hook, have to directly do them since death clears the queue.
public class OnFatalPatch {
    @SpirePatch2(
            clz = AbstractMonster.class,
            method = "die",
            paramtypez = boolean.class
    )
    public static class FatalTrigger {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractMonster __instance, boolean triggerRelics) {
            if (!__instance.halfDead && !__instance.hasPower(MinionPower.POWER_ID))
                for (AbstractPower pow: adp().powers)
                    if (pow instanceof OnFatalPower)
                        ((OnFatalPower) pow).onFatal(__instance);
        }
        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getMonsters");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}
