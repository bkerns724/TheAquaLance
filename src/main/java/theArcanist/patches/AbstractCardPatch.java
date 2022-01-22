package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class AbstractCardPatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )

    public static class AbstractCardField {
        public static SpireField<Boolean> sigil = new SpireField<>(() -> false);
        public static SpireField<Boolean> resonance = new SpireField<>(() -> false);
    }
}