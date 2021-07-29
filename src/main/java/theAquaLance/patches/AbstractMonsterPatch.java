package theAquaLance.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import javassist.CtBehavior;
import theAquaLance.powers.SoakedPower;
import theAquaLance.powers.EmbedPower;

public class AbstractMonsterPatch {
    @SpirePatch(
            clz = AbstractMonster.class,
            method = "die",
            paramtypez = { boolean.class }
    )
    public static class UnembedOnDeathPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractMonster __instance, boolean triggerRelics) {
            // Need to call something when monster dies.
            for (AbstractPower p : __instance.powers) {
                if (p instanceof EmbedPower)
                    ((EmbedPower) p).popAll();
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(StatsScreen.class, "incrementEnemySlain");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}