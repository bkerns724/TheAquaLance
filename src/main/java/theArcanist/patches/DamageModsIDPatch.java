package theArcanist.patches;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;

@SpirePatch2(
        clz = AbstractDamageModifier.class,
        method = SpirePatch.CLASS
)
public class DamageModsIDPatch {
    public static SpireField<String> ID = new SpireField<String>(() -> null);
}
