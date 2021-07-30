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
            System.out.println("Patch works");
            forAllMonstersLiving(m -> {
                System.out.println("Found monster");
                for (AbstractPower p : m.powers) {
                    if (p instanceof OnShufflePowerInterface) {
                        System.out.print("Found Power: ");
                        System.out.print(p.name);
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