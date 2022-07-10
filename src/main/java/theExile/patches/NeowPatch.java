package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.neow.NeowReward;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.ExileMod;
import theExile.TheExile;
import theExile.cards.AbstractExileCard;
import theExile.cards.ArcaneBeam;
import theExile.cards.ChaoticFlurry;
import theExile.cards.ConsumeSouls;

import java.util.ArrayList;
import static theExile.util.Wiz.*;

public class NeowPatch {
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ExileMod.makeID("NeowPatch"));
    public static final String[] TEXT = uiStrings.TEXT;

    @SpirePatch2(
            clz = NeowReward.class,
            method = "getRewardOptions"
    )
    public static class AddUniqueToNeow {
        @SpirePostfixPatch
        public static ArrayList<NeowReward.NeowRewardDef> Postfix(ArrayList<NeowReward.NeowRewardDef> __result,
                                                                  NeowReward __instance, int category) {
            if (category == 2 && adp().chosenClass == TheExile.Enums.THE_EXILE)
                __result.add(new NeowReward.NeowRewardDef(ExileMod.Enums.UNIQUE_CARD_REWARD, TEXT[0]) );
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
            if (__instance.type == ExileMod.Enums.UNIQUE_CARD_REWARD) {
                ArrayList<AbstractExileCard> list = new ArrayList<>();
                list.add(new ArcaneBeam());
                list.add(new ConsumeSouls());
                list.add(new ChaoticFlurry());
                int x = AbstractDungeon.cardRng.random(0, list.size() - 1);
                AbstractExileCard card = list.get(x);
                AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(card.makeCopy(),
                        (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            }
        }
    }
}
