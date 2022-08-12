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
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theExile.ExileMod;
import theExile.TheExile;
import theExile.cards.NullElement;
import theExile.patches.TipsInDialogPatch;
import theExile.potions.VampiricPoison;
import theExile.relics.VoidBracelet;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adRoom;
import static theExile.util.Wiz.adp;

public class VoidSpirits extends AbstractExileEvent {
    public static final String ID = makeID(VoidSpirits.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.NORMAL;

    public static final float HEALTH_BUFF = 1f;
    public static final float HEALTH_LOSS_A0 = 0.25f;
    public static final float HEALTH_LOSS_A15 = 0.3f;
    public static final int CARD_REMOVE_A0 = 4;
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

        AbstractRelic ring = new VoidBracelet();
        imageEventText.setDialogOption(options[0].replace("!RelicString!",
                FontHelper.colorString(ring.name, "g")), ring);

        int removableCards = getCardsRemoved();
        AbstractCard card = new NullElement();
        if (removableCards == 1)
            imageEventText.setDialogOption(options[1].replace("!CardString!",
                    FontHelper.colorString(card.name, "r")), card);
        else if (removableCards > 1)
            imageEventText.setDialogOption(options[2].replace("!CardString!",
                    FontHelper.colorString(card.name, "r")), card);
        else
            imageEventText.setDialogOption(options[3], true);

        AbstractPotion potion = new VampiricPoison();
        imageEventText.setDialogOption(options[4].replace("!PotionString!",
                FontHelper.colorString(potion.name, "g")));
        LargeDialogOptionButton but = imageEventText.optionList.get(2);
        TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, potion.tips);
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
                adp().masterDeck.removeCard(c);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new NullElement(), (float) Settings.WIDTH / 2.0F,
                    (float) Settings.HEIGHT / 2.0F));
            screen = CUR_SCREEN.COMPLETE;
            imageEventText.updateBodyText(descriptions[3]);
            imageEventText.clearAllDialogs();
            imageEventText.setDialogOption(options[6]);
        }
    }

    protected void buttonEffect(int buttonPressed) {
        if (screen == CUR_SCREEN.INTRO) {
            switch (buttonPressed) {
                case 0:
                    adp().decreaseMaxHealth(amount);
                    adRoom().spawnRelicAndObtain((float) Settings.WIDTH * 0.28F,
                            (float) Settings.HEIGHT / 2.0F, new VoidBracelet());
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
                    screen = CUR_SCREEN.COMPLETE;
                    adRoom().rewardAllowed = true;
                    adRoom().monsters = MonsterHelper.getEncounter("3 Darklings");
                    adRoom().rewards.clear();
                    adRoom().addGoldToRewards(30);
                    adRoom().rewards.add(new RewardItem(new VampiricPoison()));
                    adRoom().eliteTrigger = true;
                    imageEventText.clearAllDialogs();
                    enterCombatFromImage();
            }
        } else if (screen == CUR_SCREEN.COMPLETE) {
            ExileMod.logger.info("ABOUT TO OPEN MAP");
            openMap();
        }
        else if (screen == CUR_SCREEN.CARD_SCREEN){
            int removableCards = getCardsRemoved();
            if (removableCards == 1)
                AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(adp().masterDeck.getPurgeableCards()),
                        removableCards, descriptions[5], false);
            else if (removableCards > 1)
                AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(adp().masterDeck.getPurgeableCards()),
                        removableCards, descriptions[6], false);
        } else {
            ExileMod.logger.info("ABOUT TO OPEN MAP");
            openMap();
        }
    }

    public void reopen() {
        ExileMod.logger.info("REOPENED FROM COMBAT");
        imageEventText.updateBodyText(descriptions[4]);
        imageEventText.clearAllDialogs();
        imageEventText.setDialogOption(options[5]);
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