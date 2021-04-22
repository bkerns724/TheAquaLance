package theAquaLance.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import javassist.CtBehavior;
import theAquaLance.AquaLanceMod;
import theAquaLance.powers.EmbedPower;

public class AbstractMonsterPatch {
    @SpirePatch(
            clz = AbstractMonster.class,
            method = "die",
            paramtypez = { boolean.class }
    )
    public static class AbstractMonsterInsertPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn Insert(AbstractMonster __instance, boolean triggerRelics) {
            // Need to call something when monster dies.
            Integer deathCount = GameActionManagerPatch.GameActionManagerField.deathsThisCombat.get(AbstractDungeon.actionManager);
            GameActionManagerPatch.GameActionManagerField.deathsThisCombat.set(AbstractDungeon.actionManager, ++deathCount);
            for (AbstractPower p : __instance.powers) {
                if (p instanceof EmbedPower)
                    ((EmbedPower) p).unEmbed();
            }
            return SpireReturn.Continue();
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