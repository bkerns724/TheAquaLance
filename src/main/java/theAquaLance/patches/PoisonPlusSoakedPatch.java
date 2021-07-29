package theAquaLance.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theAquaLance.powers.SoakedPower;
import theAquaLance.relics.PaperPhish;
import theAquaLance.util.Wiz;

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
                float mult;
                if (Wiz.adp().hasRelic(PaperPhish.ID))
                    mult = PaperPhish.SOAK_POWER/100.0F;
                else
                    mult = SoakedPower.NORMAL_SOAK/100.0F;
                int output = (int)Math.floor(poisonAmt[0]*(1.0F + mult));
                poisonAmt[0] = output;
            }
        }
    }
}