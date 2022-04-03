package theArcanist.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import theArcanist.ArcanistMod;
import theArcanist.TheArcanist;
import theArcanist.patches.NoDiscardPotionPatch;
import theArcanist.relics.EnchantmentOils;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.adRoom;

public class MarketActOne extends AbstractArcanistEvent {
    public static final String ID = makeID(MarketActOne.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.NORMAL;
    private static final int GOLD_FOR_OIL = 100;
    private static final int GOLD_FOR_OIL_BAD = 125;
    private static final int GOLD_FOR_POTION = 75;
    private static final int GOLD_FOR_POTION_BAD = 60;

    private AbstractPotion potion;
    private CUR_SCREEN screen = CUR_SCREEN.INTRO;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ArcanistMod.makeImagePath("events/" + MarketActOne.class.getSimpleName() + ".jpg");
    }

    public static int getRelicPrice() {
        if (adp() != null && AbstractDungeon.ascensionLevel >= 15)
            return GOLD_FOR_OIL_BAD;
        else
            return GOLD_FOR_OIL;
    }

    public static int getPotionPrice() {
        if (adp() != null && AbstractDungeon.ascensionLevel >= 15)
            return GOLD_FOR_POTION_BAD;
        else
            return GOLD_FOR_POTION;
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = MarketActOne.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.dungeonIDs = new ArrayList<>();
        params.dungeonIDs.add(Exordium.ID);
        params.playerClass = TheArcanist.Enums.THE_ARCANIST;
        params.bonusCondition = () -> (adp().gold >= GOLD_FOR_OIL || adp().hasAnyPotions());
        return params;
    }

    public MarketActOne() {
        super(eventStrings, IMAGE_PATH, getRelicPrice(), getPotionPrice());
        // buy relic
        if (adp().gold < getRelicPrice()) {
            imageEventText.setDialogOption(options[0], true);
        } else {
            imageEventText.setDialogOption(options[1], new EnchantmentOils());
        }

        // sell potion
        potion = adp().getRandomPotion();
        if (potion == null)
            imageEventText.setDialogOption(options[2], true);
        else {
            imageEventText.setDialogOption(options[3].replace("!PotionString!",
                    FontHelper.colorString(potion.name, "r")));
            NoDiscardPotionPatch.PotionDiscardField.eventReserved.set(potion, true);
        }

        // leave
        imageEventText.setDialogOption(options[4]);
    }

    protected void buttonEffect(int buttonPressed) {
        if (screen == CUR_SCREEN.INTRO) {
            screen = CUR_SCREEN.COMPLETE;
            switch (buttonPressed) {
                // buy relic
                case 0:
                    adp().loseGold(getRelicPrice());
                    adRoom().spawnRelicAndObtain((float) Settings.WIDTH * 0.28F,
                            (float) Settings.HEIGHT / 2.0F, new EnchantmentOils());
                    imageEventText.updateBodyText(descriptions[1]);
                    break;
                // sell potion
                case 1:
                    adp().gainGold(getPotionPrice());
                    adp().removePotion(potion);
                    imageEventText.updateBodyText(descriptions[2]);
                    break;
                // Just leave
                case 2:
                    imageEventText.updateBodyText(descriptions[3]);
                    break;
            }
            imageEventText.clearAllDialogs();
            imageEventText.setDialogOption(options[5]);
        }
    else {
        if (potion != null)
            NoDiscardPotionPatch.PotionDiscardField.eventReserved.set(potion, false);
        openMap();
        }
    }

    private enum CUR_SCREEN {
        INTRO,
        COMPLETE;
    }
}
