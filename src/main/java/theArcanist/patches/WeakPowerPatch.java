package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.WeakPower;
import theArcanist.powers.NauseousPower;

public class WeakPowerPatch {
    @SpirePatch2(
            clz = WeakPower.class,
            method = "atDamageGive"
    )
    public static class NauseousReductionPatch {
        @SpirePostfixPatch
        public static float postfix(float __result, WeakPower __instance, float damage, DamageInfo.DamageType type) {
            if (__instance.owner.hasPower(NauseousPower.POWER_ID))
                return __result - damage * NauseousPower.WEAK_DECREASE;
            else
                return __result;
        }
    }
}
