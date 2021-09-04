package theAquaLance.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.powers.OnShufflePowerInterface;

import static theAquaLance.util.Wiz.*;

public class ShufflePatch {
    @SpirePatch(
            clz = EmptyDeckShuffleAction.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class EmptyDeckShuffleActionPatch {
        @SpirePostfixPatch
        public static void Postfix(EmptyDeckShuffleAction __instance) {
            forAllMonstersLiving(m -> {
                for (AbstractPower p : m.powers) {
                    if (p instanceof OnShufflePowerInterface) {
                        ((OnShufflePowerInterface) p).onShuffle();
                    }
                }
            });

            for (AbstractPower p : adp().powers) {
                if (p instanceof OnShufflePowerInterface) {
                    ((OnShufflePowerInterface) p).onShuffle();
                }
            }
        }
    }
}