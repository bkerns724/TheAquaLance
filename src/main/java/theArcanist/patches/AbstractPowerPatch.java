package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AbstractPowerPatch {
    @SpirePatch(clz= AbstractPower.class, method=SpirePatch.CLASS)
    public static class DynamicTextField {
        public static final SpireField<Boolean> isDynamic = new SpireField<>(() -> Boolean.FALSE);
    }
}
