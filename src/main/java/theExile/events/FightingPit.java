package theExile.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import theExile.ExileMod;
import theExile.TheExile;
import theExile.patches.TipsInDialogPatch;
import theExile.potions.PoisonousSmokeBomb;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class FightingPit extends AbstractExileEvent {
    public static final String ID = makeID(FightingPit.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.NORMAL;

    // Health Buff applied in ExileMod class
    public static final float HEALTH_BUFF = 0.25f;

    private static final int GOLD_MIN_N = 10;
    private static final int GOLD_MAX_N = 20;
    private static final int GOLD_MIN_E = 25;
    private static final int GOLD_MAX_E = 35;

    private CUR_SCREEN screen = CUR_SCREEN.INTRO;

    private boolean foughtEasy = false;
    private boolean foughtHard = false;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ExileMod.makeImagePath("events/" + FightingPit.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = FightingPit.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.playerClass = TheExile.Enums.THE_EXILE;
        params.dungeonIDs = new ArrayList<>();
        params.dungeonIDs.add(Exordium.ID);
        return params;
    }

    public FightingPit() {
        super(eventStrings, IMAGE_PATH);

        noCardsInRewards = false;
        imageEventText.setDialogOption(options[0]);
        imageEventText.setDialogOption(options[1]);
        LargeDialogOptionButton but = imageEventText.optionList.get(1);
        TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, getTips());
        imageEventText.setDialogOption(options[2]);
    }

    protected void buttonEffect(int buttonPressed) {
        if (screen == CUR_SCREEN.INTRO) {
            switch (buttonPressed) {
                case 0:
                    adRoom().monsters = MonsterHelper.getEncounter(getEasyMonster());
                    adRoom().rewardAllowed = false;
                    imageEventText.clearAllDialogs();
                    imageEventText.setDialogOption(options[3]);
                    imageEventText.updateBodyText(descriptions[1]);
                    screen = CUR_SCREEN.POST_BATTLE;
                    foughtEasy = true;
                    enterCombatFromImage();
                    break;
                case 1:
                    adRoom().monsters = MonsterHelper.getEncounter(getToughMonster());
                    adRoom().eliteTrigger = true;
                    adRoom().rewardAllowed = false;
                    imageEventText.clearAllDialogs();
                    imageEventText.setDialogOption(options[3]);
                    imageEventText.updateBodyText(descriptions[2]);
                    screen = CUR_SCREEN.POST_BATTLE;
                    foughtHard = true;
                    enterCombatFromImage();
                    break;
                case 2:
                    imageEventText.clearAllDialogs();
                    imageEventText.setDialogOption(options[4]);
                    imageEventText.updateBodyText(descriptions[3]);
                    screen = CUR_SCREEN.LEAVE;
                    break;
            }
        } else if (screen == CUR_SCREEN.POST_BATTLE) {
            adRoom().rewardAllowed = true;
            if (foughtEasy) {
                adRoom().rewards.clear();
                adRoom().rewards.add(new RewardItem(AbstractDungeon.miscRng.random(GOLD_MIN_N, GOLD_MAX_N)));
            }
            else if (foughtHard) {
                adRoom().rewards.clear();
                adRoom().addGoldToRewards(AbstractDungeon.miscRng.random(GOLD_MIN_E, GOLD_MAX_E));
                adRoom().addPotionToRewards(new PoisonousSmokeBomb());
                adRoom().addRelicToRewards(randRelic());
            }
            imageEventText.clearAllDialogs();
            imageEventText.setDialogOption(options[4]);
            imageEventText.updateBodyText(descriptions[4]);
            screen = CUR_SCREEN.LEAVE;
            AbstractDungeon.combatRewardScreen.open();
        }
        else
            openMap();
    }

    @Override
    public void reopen() {
        AbstractDungeon.resetPlayer();
        adp().drawX = (float)Settings.WIDTH * 0.25F;
        adp().preBattlePrep();
        enterImageFromCombat();
    }

    private enum CUR_SCREEN {
        INTRO,
        POST_BATTLE,
        LEAVE;
    }

    private static String getEasyMonster() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Cultist");
        list.add("Jaw Worm");
        list.add("2 Louse");
        list.add("Small Slimes");
        int x = AbstractDungeon.miscRng.random(0, list.size() - 1);
        return list.get(x);
    }

    private static String getToughMonster() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Gremlin Nob");
        list.add("Lagavulin");
        list.add("3 Sentries");
        int x = AbstractDungeon.miscRng.random(0, list.size() - 1);
        return list.get(x);
    }

    private static ArrayList<PowerTip> getTips() {
        ArrayList<PowerTip> tips = new ArrayList<>();
        tips.add(new PowerTip(eventStrings.DESCRIPTIONS[5], eventStrings.DESCRIPTIONS[6]));
        PoisonousSmokeBomb bomb = new PoisonousSmokeBomb();
        tips.add(new PowerTip(bomb.name, bomb.description));
        return tips;
    }
}