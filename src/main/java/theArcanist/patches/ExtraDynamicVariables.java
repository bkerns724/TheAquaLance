package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;
import theArcanist.ArcanistMod;
import theArcanist.powers.AbstractArcanistPower;
import theArcanist.relics.AbstractArcanistRelic;

public class ExtraDynamicVariables {
    @SpirePatch2(
            clz = AbstractCreature.class,
            method = "renderPowerTips"
    )
    @SpirePatch2(
            clz = AbstractMonster.class,
            method = "renderTip"
    )
    @SpirePatch2(
            clz = AbstractPlayer.class,
            method = "renderPowerTips"
    )
    public static class PowerDynamicVariables {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractCreature __instance) {
            for (AbstractPower pow : __instance.powers) {
                if (!(pow instanceof AbstractArcanistPower))
                    return;
                if (pow.description == null) {
                    ArcanistMod.logger.info(pow.name + " has a null description");
                    return;
                }
                AbstractArcanistPower pow2 = (AbstractArcanistPower) pow;
                pow2.description = pow2.description.replace("!A!", "#b" + pow2.amount);
                pow2.description = pow2.description.replace("!A2!", "#b" + pow2.amount2);
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(AbstractPower.class, "region48");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }

    @SpirePatch2(
            clz = AbstractRelic.class,
            method = "initializeTips"
    )
    public static class RelicDynamicVariables {
        @SpirePrefixPatch
        public static void Prefix(AbstractRelic __instance) {
            if (__instance instanceof AbstractArcanistRelic) {
                AbstractArcanistRelic relic = (AbstractArcanistRelic) __instance;
                relic.description = relic.description.replace("!R!", "#b" + relic.amount);
                relic.description = relic.description.replace("!R2!", "#b" + relic.amount2);
            }
        }
    }
}