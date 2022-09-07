package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.beyond.SensoryStone;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CtBehavior;
import theExile.cards.AbstractExileCard;
import theExile.cards.GreaterBarrier;
import theExile.cards.MaliciousDagger;
import theExile.cards.ManaBlood;

import java.util.ArrayList;

import static theExile.TheExile.Enums.THE_EXILE;
import static theExile.util.Wiz.adp;

public class SensoryStonePatch {
    @SpirePatch2(
            clz = SensoryStone.class,
            method = "reward"
    )
    public static class InsertUniqueCardPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(SensoryStone __instance, int num) {
            if (adp().chosenClass != THE_EXILE)
                return;
            ArrayList<RewardItem> rewards = AbstractDungeon.getCurrRoom().rewards;
            if (rewards.size() == 3) {
                rewards.remove(2);
                rewards.add(makeModifiedRewardItem());
            }
        }
        private static class Locator extends SpireInsertLocator {
            private Locator() {}

            @Override
            public int[] Locate(CtBehavior behavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(AbstractRoom.class, "phase");
                return LineFinder.findInOrder(behavior, matcher);
            }
        }

        private static RewardItem makeModifiedRewardItem() {
            RewardItem rewardItem = new RewardItem();
            rewardItem.hb = new Hitbox(460.0F * Settings.xScale, 90.0F * Settings.yScale);
            rewardItem.flashTimer = 0.0F;
            rewardItem.isDone = false;
            rewardItem.ignoreReward = false;
            rewardItem.redText = false;
            rewardItem.type = RewardItem.RewardType.CARD;
            rewardItem.cards = AbstractDungeon.getColorlessRewardCards();
            int x = AbstractDungeon.cardRng.random(0, rewardItem.cards.size() - 1);
            int y = AbstractDungeon.cardRng.random(0, 2);
            rewardItem.cards.remove(x);
            ArrayList<AbstractExileCard> newCards = new ArrayList<>();
            newCards.add(new GreaterBarrier());
            newCards.add(new MaliciousDagger());
            newCards.add(new ManaBlood());
            AbstractExileCard newCard = newCards.get(y);
            rewardItem.cards.add(x, newCard);

            UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RewardItem");
            rewardItem.text = uiStrings.TEXT[2];

            for (AbstractCard c : rewardItem.cards) {
                for (AbstractRelic r : AbstractDungeon.player.relics) {
                    r.onPreviewObtainCard(c);
                }
            }
            return rewardItem;
        }
    }
}