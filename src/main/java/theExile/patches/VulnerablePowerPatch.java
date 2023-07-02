package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theExile.powers.ExploitedPower;

public class VulnerablePowerPatch {
    private static float inDamage = 0;

    @SpirePatch2(
            clz = VulnerablePower.class,
            method = "atDamageReceive"
    )
    public static class SigilResetOnCantPlay {
        @SpirePrefixPatch
        public static void Prefix(float damage) {
            inDamage = damage;
        }

        @SpirePostfixPatch
        public static float Postfix(VulnerablePower __instance, float __result) {
            AbstractCreature owner = __instance.owner;
            if (!(owner.hasPower(ExploitedPower.POWER_ID)))
                return __result;
            int count = owner.getPower(ExploitedPower.POWER_ID).amount;
            return __result + count/100f * inDamage;
        }
    }
}
