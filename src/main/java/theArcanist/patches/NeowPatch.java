package theArcanist.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.neow.NeowReward;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theArcanist.ArcanistMod;
import theArcanist.TheArcanist;
import theArcanist.cards.AbstractArcanistCard;
import theArcanist.cards.ArcaneBeam;
import theArcanist.cards.ChaoticFlurry;
import theArcanist.cards.SoulFeed;

import java.util.ArrayList;
import static theArcanist.util.Wiz.*;

public class NeowPatch {
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ArcanistMod.makeID("NeowPatch"));
    public static final String[] TEXT = uiStrings.TEXT;

    @SpirePatch2(
            clz = NeowReward.class,
            method = "getRewardOptions"
    )
    public static class AddUniqueToNeow {
        @SpirePostfixPatch
        public static ArrayList<NeowReward.NeowRewardDef> Postfix(ArrayList<NeowReward.NeowRewardDef> __result,
                                                                  NeowReward __instance, int category) {
            if (category == 2 && adp().chosenClass == TheArcanist.Enums.THE_ARCANIST)
                __result.add(new NeowReward.NeowRewardDef(ArcanistMod.Enums.UNIQUE_CARD_REWARD, TEXT[0]) );
            return __result;
        }
    }

    @SpirePatch2(
            clz = NeowReward.class,
            method = "activate"
    )
    public static class activateUniqueNeow {
        @SpirePostfixPatch
        public static void Postfix(NeowReward __instance) {
            if (__instance.type == ArcanistMod.Enums.UNIQUE_CARD_REWARD) {
                ArrayList<AbstractArcanistCard> list = new ArrayList<>();
                list.add(new ArcaneBeam());
                list.add(new SoulFeed());
                list.add(new ChaoticFlurry());
                int x = AbstractDungeon.cardRng.random(0, list.size() - 1);
                AbstractArcanistCard card = list.get(x);
                AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(card.makeCopy(),
                        (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            }
        }
    }
}
