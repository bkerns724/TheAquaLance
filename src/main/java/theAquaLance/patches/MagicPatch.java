package theAquaLance.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theAquaLance.AquaLanceMod;

import static theAquaLance.util.Wiz.*;

public class MagicPatch {
    @SpirePatch(
            clz = AbstractCreature.class,
            method = "decrementBlock"
    )
    public static class ShufflePrefixPatch {
        public static SpireReturn<Integer> Prefix(AbstractCreature __instance, DamageInfo info, int damageAmount) {
            if (info.type == AquaLanceMod.Enums.MAGIC)
                return SpireReturn.Return(damageAmount);
            else
                return SpireReturn.Continue();
        }
    }
}