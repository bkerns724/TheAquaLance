package theExile.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import theExile.ExileMod;
import theExile.TheExile;
import theExile.relics.MarkOfTheVoid;
import theExile.relics.TransparentBracelet;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adRoom;
import static theExile.util.Wiz.adp;

public class VoidSpirits extends AbstractExileEvent {
    public static final String ID = makeID(VoidSpirits.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.NORMAL;

    public static final float HEALTH_BUFF = 0.5f;
    public static final float HEALTH_LOSS_A0 = 0.15f;
    public static final int CARD_REMOVE_A0 = 5;
    public static final float HEALTH_LOSS_A15 = 0.2f;
    public static final int CARD_REMOVE_A15 = 3;

    private CUR_SCREEN screen = CUR_SCREEN.INTRO;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ExileMod.makeImagePath("events/" + VoidSpirits.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = VoidSpirits.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.dungeonIDs = new ArrayList<>();
        params.dungeonIDs.add(TheBeyond.ID);
        params.playerClass = TheExile.Enums.THE_EXILE;
        return params;
    }

    public VoidSpirits() {
        super(eventStrings, IMAGE_PATH, getHealthLoss(), getCardsRemoved());
        imageEventText.updateBodyText(descriptions[0]);

        AbstractRelic ring = new TransparentBracelet();
        imageEventText.setDialogOption(options[0].replace("!RelicString!",
                FontHelper.colorString(ring.name, "g")), ring);

        if (amount2 == 1) {
            AbstractRelic mark = new MarkOfTheVoid();
            imageEventText.setDialogOption(options[1].replace("!RelicString!",
                    FontHelper.colorString(mark.name, "r")), new MarkOfTheVoid());
        }
        else if (amount2 > 1) {
            AbstractRelic mark = new MarkOfTheVoid();
            imageEventText.setDialogOption(options[2].replace("!RelicString!",
                    FontHelper.colorString(mark.name, "r")), new MarkOfTheVoid());
        }
        else
            imageEventText.setDialogOption(options[3], true);

        imageEventText.setDialogOption(options[4]);
    }

    public void update() {
        super.update();
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            int count = 0;
            float xOffsetBase = 0.5f;
            float xOffset = xOffsetBase;
            int total = AbstractDungeon.gridSelectScreen.selectedCards.size();
            if (total % 2 == 0)
                xOffsetBase += 0.09f;
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                if (count == 0)
                    xOffset = xOffsetBase;
                else if (count == 1)
                    xOffset = xOffsetBase - 0.18f;
                else if (count == 2)
                    xOffset = xOffsetBase + 0.18f;
                else if (count == 3)
                    xOffset = xOffsetBase - 0.36f;
                else if (count == 4)
                    xOffset = xOffsetBase + 0.36f;
                count++;
                AbstractDungeon.effectList.add(new PurgeCardEffect(c, xOffset*Settings.WIDTH, Settings.HEIGHT/2.0f));
                AbstractDungeon.player.masterDeck.removeCard(c);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            screen = CUR_SCREEN.COMPLETE;
            imageEventText.updateBodyText(descriptions[3]);
            imageEventText.clearAllDialogs();
            imageEventText.setDialogOption(options[6]);
            adRoom().spawnRelicAndObtain((float) Settings.WIDTH * 0.28F,
                    (float) Settings.HEIGHT / 2.0F, new MarkOfTheVoid());
        }
    }

    protected void buttonEffect(int buttonPressed) {
        if (screen == CUR_SCREEN.INTRO) {
            switch (buttonPressed) {
                case 0:
                    adp().decreaseMaxHealth(amount);
                    adRoom().spawnRelicAndObtain((float) Settings.WIDTH * 0.28F,
                            (float) Settings.HEIGHT / 2.0F, new TransparentBracelet());
                    screen = CUR_SCREEN.COMPLETE;
                    imageEventText.updateBodyText(descriptions[1]);
                    imageEventText.clearAllDialogs();
                    imageEventText.setDialogOption(options[6]);
                    break;
                case 1:
                    screen = CUR_SCREEN.CARD_SCREEN;
                    imageEventText.updateBodyText(descriptions[2]);
                    imageEventText.clearAllDialogs();
                    imageEventText.setDialogOption(options[5]);
                    break;
                case 2:
                    adRoom().monsters = MonsterHelper.getEncounter("3 Darklings");
                    adRoom().rewards.clear();
                    adRoom().rewardAllowed = false;
                    enterCombatFromImage();
                    screen = CUR_SCREEN.COMPLETE;
                    imageEventText.updateBodyText(descriptions[4]);
                    imageEventText.clearAllDialogs();
                    imageEventText.setDialogOption(options[6]);
                    break;
            }
        } else if (screen == CUR_SCREEN.COMPLETE)
            openMap();
        else {
            if (amount2 == 1)
                AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(adp().masterDeck.getPurgeableCards()),
                        amount2, descriptions[5], false);
            else if (amount2 > 1)
                AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(adp().masterDeck.getPurgeableCards()),
                        amount2, descriptions[6], false);
        }
    }

    public void reopen() {
        AbstractDungeon.resetPlayer();
        AbstractDungeon.player.drawX = (float) Settings.WIDTH * 0.25F;
        AbstractDungeon.player.preBattlePrep();
        enterImageFromCombat();
    }

    private enum CUR_SCREEN {
        INTRO,
        CARD_SCREEN,
        COMPLETE;
    }

    private static int getHealthLoss() {
        if (AbstractDungeon.ascensionLevel < 15)
            return (int)(adp().maxHealth*HEALTH_LOSS_A0);
        return (int)(adp().maxHealth*HEALTH_LOSS_A15);
    }

    private static int getCardsRemoved() {
        int x = CardGroup.getGroupWithoutBottledCards(adp().masterDeck.getPurgeableCards()).size();
        if (AbstractDungeon.ascensionLevel < 15)
            return Math.min(CARD_REMOVE_A0, x);
        return Math.min(CARD_REMOVE_A15, x);
    }
}