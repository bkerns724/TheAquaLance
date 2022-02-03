package theArcanist.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import javassist.CtBehavior;
import theArcanist.ArcanistMod;
import theArcanist.cards.AbstractArcanistCard;
import theArcanist.cards.AbstractSigilCard;

import java.util.ArrayList;

public class LoreTooltipPatch {
    private static final Color LORE_COLOR = Color.VIOLET.cpy();

    @SpirePatch(
            clz = TipHelper.class,
            method = "renderKeywords"
    )
    public static class TipHelperRenderLorePatch{
        @SpirePostfixPatch
        public static void TipHelperRenderLore(float x, @ByRef float[] y, SpriteBatch sb,
                                               ArrayList<String> keywords,
                                               float ___TIP_DESC_LINE_SPACING, float ___BODY_TEXT_WIDTH,
                                               float ___BOX_EDGE_H, float ___SHADOW_DIST_X, float ___SHADOW_DIST_Y,
                                               float ___BOX_W, float ___BOX_BODY_H, float ___TEXT_OFFSET_X,
                                               Color ___BASE_COLOR, AbstractCard ___card) {
            String s;
            if (!(___card instanceof AbstractArcanistCard))
                return;
            else if (((AbstractArcanistCard) ___card).lore.equals(ArcanistMod.EMPTY_LORE_STRING))
                return;
            else
                s = ((AbstractArcanistCard)___card).lore;

            float h = -FontHelper.getSmartHeight(FontHelper.tipBodyFont, s,
                    ___BODY_TEXT_WIDTH, ___TIP_DESC_LINE_SPACING) - 7.0F * Settings.scale;

            sb.setColor(Settings.TOP_PANEL_SHADOW_COLOR);
            sb.draw(ImageMaster.KEYWORD_TOP, x + ___SHADOW_DIST_X, y[0] - ___SHADOW_DIST_Y, ___BOX_W, ___BOX_EDGE_H);
            sb.draw(ImageMaster.KEYWORD_BODY, x + ___SHADOW_DIST_X, y[0] - h - ___BOX_EDGE_H - ___SHADOW_DIST_Y, ___BOX_W, h + ___BOX_EDGE_H);
            sb.draw(ImageMaster.KEYWORD_BOT, x + ___SHADOW_DIST_X, y[0] - h - ___BOX_BODY_H - ___SHADOW_DIST_Y, ___BOX_W, ___BOX_EDGE_H);
            sb.setColor(LORE_COLOR);
            sb.draw(ImageMaster.KEYWORD_TOP, x, y[0], ___BOX_W, ___BOX_EDGE_H);
            sb.draw(ImageMaster.KEYWORD_BODY, x, y[0] - h - ___BOX_EDGE_H, ___BOX_W, h + ___BOX_EDGE_H);
            sb.draw(ImageMaster.KEYWORD_BOT, x, y[0] - h - ___BOX_BODY_H, ___BOX_W, ___BOX_EDGE_H);

            FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, s,x + ___TEXT_OFFSET_X,
                    y[0], ___BODY_TEXT_WIDTH, ___TIP_DESC_LINE_SPACING,
                    ___BASE_COLOR);

                y[0] -= h + ___BOX_EDGE_H * 3.15F;
        }
        @SpirePrefixPatch
        public static void TipHelperSpacing(float x, @ByRef float[] y, SpriteBatch sb, ArrayList<String> keywords,
                                            AbstractCard ___card) {
            if (!(___card instanceof AbstractArcanistCard))
                return;
            else if (((AbstractArcanistCard) ___card).lore.equals(ArcanistMod.EMPTY_LORE_STRING))
                return;
            if (keywords.size() >= 3)
                y[0] += 62.0f * Settings.scale;
            if (keywords.size() >= 2)
                y[0] += 31.0f * Settings.scale;
        }
    }
}