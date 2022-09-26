package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.powers.ManaBurnPower;

import static theExile.util.Wiz.adp;

@SpirePatch2(
        clz = AbstractCard.class,
        method = "freeToPlay"
)
public class ManaBurnPatch {
    @SpirePrefixPatch
    public static SpireReturn<Boolean> Prefix() {
        if (adp().hasPower(ManaBurnPower.POWER_ID))
            return SpireReturn.Return(true);
        return SpireReturn.Return(false);
    }
}
