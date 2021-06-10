package theAquaLance.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theAquaLance.powers.SoakedPower;

public class PoisonPlusSoakedPatch {
    @SpirePatch(
            clz = AbstractCreature.class,
            method = "renderRedHealthBar",
            paramtypez = {
                    SpriteBatch.class,
                    float.class,
                    float.class
            }
    )

    public static class AbstractCreatureHealthBarPatch {
        @SpireInsertPatch(
                rloc = 29,
                localvars = {"poisonAmt"}
        )
        public static void Insert(AbstractCreature __instance, SpriteBatch sb, float x, float y, @ByRef int[] poisonAmt) {
            if (poisonAmt[0] > 0 && __instance.hasPower(SoakedPower.POWER_ID)) {
                int drownAmount = __instance.getPower(SoakedPower.POWER_ID).amount;
                poisonAmt[0] += drownAmount;
            }
        }
    }
}