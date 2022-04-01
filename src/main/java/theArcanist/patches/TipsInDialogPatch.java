package theArcanist.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;

import java.util.ArrayList;

import static theArcanist.util.Wiz.adp;

public class TipsInDialogPatch {
    @SpirePatch2(
            clz = GenericEventDialog.class,
            method = "render"
    )
    public static class CallPotionPreviewRender {
        @SpirePostfixPatch
        public static void Postfix(GenericEventDialog __instance, SpriteBatch sb) {
            if ((boolean)ReflectionHacks.getPrivate(__instance, GenericEventDialog.class, "show") &&
                    !adp().isDead) {
                for (LargeDialogOptionButton but : __instance.optionList)
                    RenderTipsPreview(but, sb);
            }
        }
    }

    @SpirePatch2(
            clz = LargeDialogOptionButton.class,
            method = SpirePatch.CLASS
    )
    public static class ButtonPreviewField {
        public static SpireField<ArrayList<PowerTip>> previewTips = new SpireField<ArrayList<PowerTip>>(() -> null);
    }

    public static void RenderTipsPreview(LargeDialogOptionButton but, SpriteBatch sb) {
        ArrayList<PowerTip> tips = ButtonPreviewField.previewTips.get(but);
        if (!Settings.isControllerMode && but.hb.hovered && tips != null) {
            TipHelper.queuePowerTips(470.0F * Settings.scale,
                    (float) InputHelper.mY + TipHelper.calculateToAvoidOffscreen(tips, InputHelper.mY), tips);
        }
    }
}
