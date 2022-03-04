package theArcanist.patches;

import basemod.abstracts.CustomEnergyOrb;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theArcanist.relics.AbstractClickRelic;

import static theArcanist.util.Wiz.adp;

// Thanks to Alison for how to patch this in for proper update timing
public class RelicWithUIPatch {
    @SpirePatch2(clz = CustomEnergyOrb.class, method = "renderOrb")
    public static class RenderElementAfterOrb {
        @SpirePostfixPatch
        public static void renderPls(SpriteBatch sb) {
            if (adp() == null || adp().relics == null)
                return;

            for (AbstractRelic relic : adp().relics) {
                if (relic instanceof AbstractClickRelic) {
                    AbstractClickRelic cRelic = (AbstractClickRelic) relic;
                    if (cRelic.grayscale)
                        cRelic.getElement().render(sb, Color.GRAY);
                    else
                        cRelic.getElement().render(sb, Color.WHITE);
                }
            }
        }
    }

    @SpirePatch2(clz = CustomEnergyOrb.class, method = "updateOrb")
    public static class UpdateElementAfterOrb {
        @SpirePostfixPatch
        public static void updatePls() {
            if (adp() == null || adp().relics == null)
                return;

            for (AbstractRelic relic : adp().relics) {
                if (relic instanceof AbstractClickRelic)
                    ((AbstractClickRelic) relic).getElement().update();
            }
        }
    }
}
