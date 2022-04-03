package theArcanist.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.Sozu;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import theArcanist.ArcanistMod;
import theArcanist.TheArcanist;
import theArcanist.patches.NoDiscardPotionPatch;
import theArcanist.patches.TipsInDialogPatch;
import theArcanist.potions.ToxicOil;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;

public class MarketActTwo extends AbstractArcanistEvent {
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
        IMAGE_PATH = ArcanistMod.makeImagePath("events/" + MarketActTwo.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = MarketActTwo.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.dungeonIDs = new ArrayList<>();
        params.dungeonIDs.add(TheCity.ID);
        params.playerClass = TheArcanist.Enums.THE_ARCANIST;
        params.bonusCondition = () -> ((adp().gold >= getGoldAmount() || haveRarePot()) && !adp().hasRelic(Sozu.ID));
        return params;
    }

    public MarketActTwo() {
        super(eventStrings, IMAGE_PATH, getGoldAmount());

        if (adp().gold >= getGoldAmount()) {
            imageEventText.setDialogOption(options[0]);
            LargeDialogOptionButton but = imageEventText.optionList.get(0);
            TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, getPowerTips());
        } else
            imageEventText.setDialogOption(options[1], true);

        if (haveRarePot()) {
            for (AbstractPotion p : adp().potions) {
                if (p.rarity == AbstractPotion.PotionRarity.RARE)
                    potion = p;
            }
            if (potion != null) {
                imageEventText.setDialogOption(options[2].replace("!PotionString!",
                        FontHelper.colorString(potion.name, "r")));
                LargeDialogOptionButton but = imageEventText.optionList.get(1);
                TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, getPowerTips());
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
                    adp().obtainPotion(new ToxicOil());
                    imageEventText.updateBodyText(descriptions[1]);
                    break;
                case 1:
                    adp().removePotion(potion);
                    adp().obtainPotion(new ToxicOil());
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

    private static ArrayList<PowerTip> getPowerTips() {
        return new ToxicOil().tips;
    }
}