package theArcanist.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import theArcanist.potions.AbstractArcanistPotion;

import static com.megacrit.cardcrawl.cards.AbstractCard.IMG_HEIGHT;
import static com.megacrit.cardcrawl.cards.AbstractCard.IMG_WIDTH;

@SpirePatch2(
        clz = AbstractPotion.class,
        method = "renderOutline",
        paramtypez = {SpriteBatch.class}
)
public class CardPreviewInPotionPatch {
    @SpirePrefixPatch
    public static void Prefix(AbstractPotion __instance, SpriteBatch sb) {
        if (!(__instance instanceof AbstractArcanistPotion))
            return;
        AbstractArcanistPotion potion = (AbstractArcanistPotion)__instance;
        if (potion.cardToPreview == null)
            return;
        // Numbers are based off of preview card code in AbstractCard
        float tmpScale = 0.7f * 0.8F;
        if (potion.hb.cX > (float) Settings.WIDTH * 0.75F) {
            potion.cardToPreview.current_x = potion.hb.cX + (IMG_WIDTH / 2.0F + IMG_WIDTH / 2.0F * 0.8F + 16.0F) * 0.7f;
        } else {
            potion.cardToPreview.current_x = potion.hb.cX - (IMG_WIDTH / 2.0F + IMG_WIDTH / 2.0F * 0.8F + 16.0F) * 0.7f;
        }

        potion.cardToPreview.current_y = potion.hb.cY + (IMG_HEIGHT / 2.0F - IMG_HEIGHT / 2.0F * 0.8F) * 0.7f;
        potion.cardToPreview.drawScale = tmpScale;
        potion.cardToPreview.render(sb);
    }
}
