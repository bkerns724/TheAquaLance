package theExile.patches;

import basemod.abstracts.CustomEnergyOrb;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbBlue;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbGreen;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbPurple;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbRed;
import theExile.util.ClickableForPower;

// Thanks to Alison for how to patch this in for proper update timing
public class ClickyPatch
{
    @SpirePatch2(clz = EnergyOrbRed.class, method = "renderOrb")
    @SpirePatch2(clz = EnergyOrbGreen.class, method = "renderOrb")
    @SpirePatch2(clz = EnergyOrbBlue.class, method = "renderOrb")
    @SpirePatch2(clz = EnergyOrbPurple.class, method = "renderOrb")
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

    @SpirePatch2(clz = EnergyOrbRed.class, method = "updateOrb")
    @SpirePatch2(clz = EnergyOrbGreen.class, method = "updateOrb")
    @SpirePatch2(clz = EnergyOrbBlue.class, method = "updateOrb")
    @SpirePatch2(clz = EnergyOrbPurple.class, method = "updateOrb")
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