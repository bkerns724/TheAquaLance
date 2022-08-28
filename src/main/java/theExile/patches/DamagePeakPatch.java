package theExile.patches;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.ExileMod;

@SpirePatch2(clz = DamageModifierManager.class, method = "addModifier")
public class DamagePeakPatch {
    @SpirePrefixPatch
    public static void prefix(AbstractCard card, AbstractDamageModifier damageMod) {
        ExileMod.logger.info(card.name);
        ExileMod.logger.info(damageMod.getClass().getSimpleName());
    }
}
