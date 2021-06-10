package theAquaLance.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AbstractCardPatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )

    public static class AbstractCardField {
        public static SpireField<Boolean> replenish = new SpireField<>(() -> false);
        public static SpireField<Boolean> sigil = new SpireField<>(() -> false);
        public static SpireField<Boolean> boomerang = new SpireField<>(() -> false);
    }
}