//  Keeping this around so I can quickly pass on a simpler version to people who want to do such a
//  thing without the potion lab stuff

/*
package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.random.Random;
import theArcanist.ArcanistMod;

import java.util.ArrayList;
public class EventRarityPotionsPatchSmall {
    private static ArrayList<String> tempList = new ArrayList<>();

    @SpirePatch2(
            clz = PotionHelper.class,
            method = "getRandomPotion",
            paramtypez = {}
    )
    @SpirePatch2(
            clz = PotionHelper.class,
            method = "getRandomPotion",
            paramtypez = {Random.class}
    )
    public static class NoEventPotionsFromHelper {
        @SpirePrefixPatch
        public static void Prefix() {
            tempList = new ArrayList<>();
            tempList.addAll(PotionHelper.potions);
            tempList.removeIf(str -> (PotionHelper.getPotion(str).rarity != ArcanistMod.Enums.EVENT));
            PotionHelper.potions.removeIf(str -> (PotionHelper.getPotion(str).rarity == ArcanistMod.Enums.EVENT));
        }

        @SpirePostfixPatch
        public static void Postfix() {
            PotionHelper.potions.addAll(tempList);
        }
    }
}
*/