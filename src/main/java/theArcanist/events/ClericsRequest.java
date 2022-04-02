package theArcanist.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import theArcanist.ArcanistMod;
import theArcanist.TheArcanist;
import theArcanist.cards.Wrath;
import theArcanist.patches.TipsInDialogPatch;
import theArcanist.relics.HexedStaff;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adRoom;
import static theArcanist.util.Wiz.adp;

public class ClericsRequest extends AbstractArcanistEvent {
    public static final String ID = makeID(ClericsRequest.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.NORMAL;

    private final static int PRICE_A0 = 150;
    private final static int PRICE_A15 = 200;

    private final static float MAX_HP_GAIN = 0.1f;

    private CUR_SCREEN screen = CUR_SCREEN.INTRO;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ArcanistMod.makeImagePath("events/" + ClericsRequest.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = ClericsRequest.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.dungeonIDs = new ArrayList<>();
        params.dungeonIDs.add(Exordium.ID);
        params.playerClass = TheArcanist.Enums.THE_ARCANIST;
        return params;
    }

    public ClericsRequest() {
        super(eventStrings, IMAGE_PATH, getMaxHpPrice(), (int)(MAX_HP_GAIN*adp().maxHealth));

        amount2 = (int)(MAX_HP_GAIN*adp().maxHealth);

        imageEventText.updateBodyText(descriptions[0]);
        imageEventText.setDialogOption(options[0], new Wrath());
        LargeDialogOptionButton but = imageEventText.optionList.get(0);
        TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, getTips());

        if (adp().gold >= getMaxHpPrice())
            imageEventText.setDialogOption(options[1]);
        else
            imageEventText.setDialogOption(options[2], true);
        imageEventText.setDialogOption(options[3]);
    }

    protected void buttonEffect(int buttonPressed) {
        if (screen == CUR_SCREEN.INTRO) {
            screen = CUR_SCREEN.COMPLETE;
            switch (buttonPressed) {
                case 0:
                    adRoom().spawnRelicAndObtain((float) Settings.WIDTH * 0.28F,
                            (float) Settings.HEIGHT / 2.0F, new HexedStaff());
                    imageEventText.updateBodyText(descriptions[1]);
                    break;
                case 1:
                    adp().loseGold(getMaxHpPrice());
                    AbstractDungeon.player.increaseMaxHp((int)(MAX_HP_GAIN*adp().maxHealth), true);
                    imageEventText.updateBodyText(descriptions[2]);
                    break;
                case 2:
                    imageEventText.updateBodyText(descriptions[3]);
                    break;
            }
            imageEventText.clearAllDialogs();
            imageEventText.setDialogOption(options[4]);
        } else
            openMap();
    }

    private enum CUR_SCREEN {
        INTRO,
        COMPLETE;
    }

    private ArrayList<PowerTip> getTips() {
        return new HexedStaff().tips;
    }

    private static int getMaxHpPrice() {
        if (AbstractDungeon.ascensionLevel < 15)
            return PRICE_A0;
        else
            return PRICE_A15;
    }
}