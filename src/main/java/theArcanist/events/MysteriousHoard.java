package theArcanist.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.cards.curses.Pain;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theArcanist.ArcanistMod;
import theArcanist.TheArcanist;
import theArcanist.cards.Greed;
import theArcanist.relics.SoulLamp;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adRoom;
import static theArcanist.util.Wiz.adp;

public class MysteriousHoard extends AbstractArcanistEvent {
    public static final String ID = makeID(MysteriousHoard.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.NORMAL;
    private static final int GOLD_A0 = 150;
    private static final int GOLD_A15 = 110;

    private CUR_SCREEN screen = CUR_SCREEN.INTRO;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ArcanistMod.makeImagePath("events/" + MysteriousHoard.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = MysteriousHoard.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.dungeonIDs = new ArrayList<>();
        params.dungeonIDs.add(TheBeyond.ID);
        params.playerClass = TheArcanist.Enums.THE_ARCANIST;
        return params;
    }

    public MysteriousHoard() {
        super(eventStrings, IMAGE_PATH, getGold());

        imageEventText.setDialogOption(options[0], new Pain(), new SoulLamp());
        imageEventText.setDialogOption(options[1], new Greed());
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