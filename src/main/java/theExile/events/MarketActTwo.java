package theExile.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.Sozu;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import theExile.ExileMod;
import theExile.TheExile;
import theExile.patches.NoDiscardPotionPatch;
import theExile.patches.TipsInDialogPatch;
import theExile.potions.ToxicOil;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class MarketActTwo extends AbstractExileEvent {
    public static final String ID = makeID(MarketActTwo.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.NORMAL;
    private static final int GOLD_A0 = 150;
    private static final int GOLD_A15 = 150;

    private AbstractPotion potion = null;

    private CUR_SCREEN screen = CUR_SCREEN.INTRO;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ExileMod.makeImagePath("events/" + MarketActTwo.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = MarketActTwo.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.playerClass = TheExile.Enums.THE_EXILE;
        params.bonusCondition = () -> ((adp().gold >= getGoldAmount() || haveRarePot()) && !adp().hasRelic(Sozu.ID));
        return params;
    }

    public MarketActTwo() {
        super(eventStrings, IMAGE_PATH, getGoldAmount());

        ToxicOil oil = new ToxicOil();

        if (adp().gold >= getGoldAmount()) {
            imageEventText.setDialogOption(options[0].replace("!PotionString2!",
                    FontHelper.colorString(oil.name, "g")));
            LargeDialogOptionButton but = imageEventText.optionList.get(0);
            TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, oil.tips);
        } else
            imageEventText.setDialogOption(options[1], true);

        if (haveRarePot()) {
            for (AbstractPotion p : adp().potions) {
                if (p.rarity == AbstractPotion.PotionRarity.RARE)
                    potion = p;
            }
            if (potion != null) {
                String opt = options[2].replace("!PotionString!", FontHelper.colorString(potion.name, "r"));
                opt = opt.replace("!PotionString2!", FontHelper.colorString(oil.name, "g"));
                imageEventText.setDialogOption(opt);
                LargeDialogOptionButton but = imageEventText.optionList.get(1);
                TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, oil.tips);
                NoDiscardPotionPatch.PotionDiscardField.eventReserved.set(potion, true);
            } else {
                imageEventText.setDialogOption(options[3], true);
            }
        } else
            imageEventText.setDialogOption(options[3], true);
        imageEventText.setDialogOption(options[4]);
    }

    protected void buttonEffect(int buttonPressed) {
        if (screen == CUR_SCREEN.INTRO) {
            screen = CUR_SCREEN.COMPLETE;
            switch (buttonPressed) {
                case 0:
                    adp().loseGold(getGoldAmount());
                    AbstractDungeon.getCurrRoom().rewards.clear();
                    AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(new ToxicOil()));
                    AbstractDungeon.combatRewardScreen.open();
                    imageEventText.updateBodyText(descriptions[1]);
                    break;
                case 1:
                    adp().removePotion(potion);
                    AbstractDungeon.getCurrRoom().rewards.clear();
                    AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(new ToxicOil()));
                    AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                    AbstractDungeon.combatRewardScreen.open();
                    imageEventText.updateBodyText(descriptions[2]);
                    break;
                case 2:
                    imageEventText.updateBodyText(descriptions[3]);
                    break;
            }
            imageEventText.clearAllDialogs();
            imageEventText.setDialogOption(options[5]);
        } else {
            if (potion != null)
                NoDiscardPotionPatch.PotionDiscardField.eventReserved.set(potion, false);
            openMap();
        }
    }

    private enum CUR_SCREEN {
        INTRO,
        COMPLETE;
    }

    private static int getGoldAmount() {
        if (AbstractDungeon.ascensionLevel < 15)
            return GOLD_A0;
        else
            return GOLD_A15;
    }

    private static boolean haveRarePot() {
        for (AbstractPotion potion : adp().potions)
            if (potion.rarity == AbstractPotion.PotionRarity.RARE)
                return true;
        return false;
    }
}