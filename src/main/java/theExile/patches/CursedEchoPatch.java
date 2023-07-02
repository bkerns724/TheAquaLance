package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CursedEchoPatch {
    @SpirePatch2(
            clz = AbstractPower.class,
            method = SpirePatch.CLASS
    )
    public static class CursedEchoField {
        public static SpireField<Boolean> echo = new SpireField<>(() -> false);
    }
}
