package theAquaLance.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class LagavulinFixPatch {
    @SpirePatch(
            clz = FlashAtkImgEffect.class,
            method = "render"
    )
    public static class LagavulinNotInvisiblePatch {
        @SpirePostfixPatch
        public static void Postfix(FlashAtkImgEffect __instance, SpriteBatch sb) {
            //sb.setColor(Color.WHITE); // Does not need .cpy()
        }
    }
}