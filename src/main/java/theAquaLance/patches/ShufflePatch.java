package theAquaLance.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.powers.OnShufflePowerInterface;

import static theAquaLance.util.Wiz.*;

public class ShufflePatch {
    @SpirePatch(
            clz = ShuffleAction.class,
            method = "update"
    )
    public static class ShufflePrefixPatch {
        public static void Prefix(ShuffleAction __instance) {
            boolean triggerHooks = ReflectionHacks.getPrivate(__instance, ShuffleAction.class, "triggerRelics");
            if (triggerHooks) {
                forAllMonstersLiving(m -> {
                    for (AbstractPower p : m.powers)
                        if (p instanceof OnShufflePowerInterface)
                            ((OnShufflePowerInterface) p).onShuffle();
                });

                for (AbstractPower p : adp().powers)
                    if (p instanceof OnShufflePowerInterface)
                        ((OnShufflePowerInterface) p).onShuffle();
            }
        }
    }
}