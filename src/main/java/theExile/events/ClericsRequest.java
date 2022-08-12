package theExile.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import theExile.ExileMod;
import theExile.TheExile;
import theExile.cards.Wrath;
import theExile.patches.TipsInDialogPatch;
import theExile.relics.HexedStaff;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adRoom;
import static theExile.util.Wiz.adp;

public class ClericsRequest extends AbstractExileEvent {
    public static final String ID = makeID(ClericsRequest.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.NORMAL;

    private final static int PRICE_A0 = 100;
    private final static int PRICE_A15 = 150;
    private final static float MAX_HP_GAIN = 0.1f;

    private int maxHpGain = 0;

    private CUR_SCREEN screen = CUR_SCREEN.INTRO;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ExileMod.makeImagePath("events/" + ClericsRequest.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = ClericsRequest.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.dungeonIDs = new ArrayList<>();
        params.dungeonIDs.add(Exordium.ID);
        params.playerClass = TheExile.Enums.THE_EXILE;
        params.bonusCondition = () -> (adp().gold >= getMaxHpPrice());
        return params;
    }

    public ClericsRequest() {
        super(eventStrings, IMAGE_PATH, getMaxHpPrice(), getMaxHpAmount());
        maxHpGain = amount2;

        imageEventText.updateBodyText(descriptions[0]);
        String opt = options[0];
        Wrath wrath = new Wrath();
        HexedStaff staff = new HexedStaff();
        opt = opt.replace("!RelicString!", FontHelper.colorString(staff.name, "g"));
        opt = opt.replace("!CardString!", FontHelper.colorString(wrath.name, "r"));
        imageEventText.setDialogOption(opt, wrath);
        LargeDialogOptionButton but = imageEventText.optionList.get(0);
        TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, staff.tips);

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
                    AbstractDungeon.player.increaseMaxHp(maxHpGain, true);
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

    private static int getMaxHpPrice() {
        if (AbstractDungeon.ascensionLevel < 15)
            return PRICE_A0;
        else
            return PRICE_A15;
    }

    private static int getMaxHpAmount() {
        return Math.max(1, (int)(MAX_HP_GAIN*adp().maxHealth));
    }
}