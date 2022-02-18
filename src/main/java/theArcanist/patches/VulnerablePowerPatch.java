package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theArcanist.powers.CorrodedPower;

public class VulnerablePowerPatch {
    @SpirePatch2(
            clz = VulnerablePower.class,
            method = "atDamageReceive"
    )
    public static class CorrodedBoostPatch {
        @SpirePostfixPatch
        public static float postfix(float __result, VulnerablePower __instance, float damage, DamageInfo.DamageType type) {
            if (__instance.owner.hasPower(CorrodedPower.POWER_ID))
                return __result + damage * CorrodedPower.VULN_INCREASE;
            else
                return __result;
        }
    }
}
