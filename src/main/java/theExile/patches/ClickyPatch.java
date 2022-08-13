package theExile.patches;

import basemod.abstracts.CustomEnergyOrb;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import theExile.util.ClickableForPower;

// Thanks to Alison for how to patch this in for proper update timing
public class ClickyPatch
{
    @SpirePatch2(clz = CustomEnergyOrb.class, method = "renderOrb")
    public static class RenderElementAfterOrb
    {
        @SpirePostfixPatch
        public static void renderPls(SpriteBatch sb)
        {
            for (ClickableForPower clicky : ClickableForPower.getClickableList())
                clicky.render(sb);
        }
    }

    @SpirePatch2(clz = CustomEnergyOrb.class, method = "updateOrb")
    public static class UpdateElementAfterOrb
    {
        @SpirePostfixPatch
        public static void updatePls()
        {
            ClickableForPower.updateClickableList();
            for (ClickableForPower clicky : ClickableForPower.getClickableList())
                clicky.update();
        }
    }
}