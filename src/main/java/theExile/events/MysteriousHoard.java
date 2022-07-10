package theExile.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.cards.curses.Pain;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.ExileMod;
import theExile.TheExile;
import theExile.cards.Greed;
import theExile.relics.SoulLamp;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adRoom;
import static theExile.util.Wiz.adp;

public class MysteriousHoard extends AbstractExileEvent {
    public static final String ID = makeID(MysteriousHoard.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.NORMAL;
    private static final int GOLD_A0 = 150;
    private static final int GOLD_A15 = 110;

    private CUR_SCREEN screen = CUR_SCREEN.INTRO;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ExileMod.makeImagePath("events/" + MysteriousHoard.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = MysteriousHoard.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.dungeonIDs = new ArrayList<>();
        params.dungeonIDs.add(TheBeyond.ID);
        params.playerClass = TheExile.Enums.THE_EXILE;
        return params;
    }

    public MysteriousHoard() {
        super(eventStrings, IMAGE_PATH, getGold());

        Pain pain = new Pain();
        SoulLamp lamp = new SoulLamp();
        Greed greed = new Greed();

        String opt = options[0].replace("!CardString!", FontHelper.colorString(pain.name, "r"));
        opt = opt.replace("!RelicString!", FontHelper.colorString(lamp.name, "g"));

        imageEventText.setDialogOption(opt, pain, lamp);
        imageEventText.setDialogOption(options[1].replace("!CardString!",
                FontHelper.colorString(greed.name, "r")), greed);
        imageEventText.setDialogOption(options[2]);
    }

    protected void buttonEffect(int buttonPressed) {
        if (screen == CUR_SCREEN.INTRO) {
            screen = CUR_SCREEN.COMPLETE;
            switch (buttonPressed) {
                case 0:
                    adRoom().spawnRelicAndObtain((float) Settings.WIDTH * 0.28F,
                            (float) Settings.HEIGHT / 2.0F, new SoulLamp());
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Pain(), (float) Settings.WIDTH / 2.0F,
                            (float) Settings.HEIGHT / 2.0F));
                    imageEventText.updateBodyText(descriptions[1]);
                    break;
                case 1:
                    adp().gainGold(getGold());
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Greed(), (float) Settings.WIDTH / 2.0F,
                            (float) Settings.HEIGHT / 2.0F));
                    imageEventText.updateBodyText(descriptions[2]);
                    break;
                case 2:
                    imageEventText.updateBodyText(descriptions[3]);
                    break;
            }
            imageEventText.clearAllDialogs();
            imageEventText.setDialogOption(options[3]);
        } else
            openMap();
    }

    private enum CUR_SCREEN {
        INTRO,
        COMPLETE;
    }

    private static int getGold() {
        if (AbstractDungeon.ascensionLevel < 15)
            return  GOLD_A0;
        else
            return GOLD_A15;
    }
}