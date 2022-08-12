package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import theExile.powers.AtPlayerStartOfTurnPower;

import static theExile.util.Wiz.forAllMonstersLiving;

public class StartOfTurnPowerPatch {
    @SpirePatch2(
            clz = GameActionManager.class,
            method = "getNextAction"
    )
    public static class StartOfTurnTrigger {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(GameActionManager __instance) {
            forAllMonstersLiving(m -> {
                for (AbstractPower pow : m.powers)
                    if (pow instanceof AtPlayerStartOfTurnPower)
                        ((AtPlayerStartOfTurnPower) pow).atPlayerStartOfTurn();
            });
        }
        public static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfTurnPostDrawPowers");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }
    }
}
