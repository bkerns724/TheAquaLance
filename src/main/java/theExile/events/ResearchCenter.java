package theExile.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theExile.ExileMod;
import theExile.TheExile;
import theExile.cards.AbstractExileCard;
import theExile.cards.Dualcast;
import theExile.damagemods.*;
import theExile.patches.TipsInDialogPatch;
import theExile.potions.SteelhidePotion;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adRoom;
import static theExile.util.Wiz.adp;

public class ResearchCenter extends AbstractExileEvent {
    public static final String ID = makeID(ResearchCenter.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.SHRINE;
    private static final int GOLD_A0 = 150;
    private static final int GOLD_A15 = 200;

    private boolean pickedElement = false;
    private boolean pickedSigil = false;
    private AbstractExileCard.elenum element = null;

    private CUR_SCREEN screen = CUR_SCREEN.INTRO;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ExileMod.makeImagePath("events/" + ResearchCenter.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = ResearchCenter.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.dungeonIDs = new ArrayList<>();
        params.dungeonIDs.add(TheCity.ID);
        params.playerClass = TheExile.Enums.THE_EXILE;
        params.bonusCondition = () -> (adp().gold >= getGoldCost());
        return params;
    }

    public ResearchCenter() {
        super(eventStrings, IMAGE_PATH, getGoldCost());
        noCardsInRewards = true;

        if (adp().gold >= getGoldCost() && hasCardForElement())
            imageEventText.setDialogOption(options[0]);
        else
            imageEventText.setDialogOption(options[1], true);

        if (hasCardForSigil() && adp().gold >= getGoldCost())
            imageEventText.setDialogOption(options[7]);
        else
            imageEventText.setDialogOption(options[8], true);

        if (adp().gold > getGoldCost()) {
            imageEventText.setDialogOption(options[9].replace("!PotionString!",
                    FontHelper.colorString(new SteelhidePotion().name, "g")));
            LargeDialogOptionButton but = imageEventText.optionList.get(2);
            TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, new SteelhidePotion().tips);
        }
        else
            imageEventText.setDialogOption(options[10], true);

        imageEventText.setDialogOption(options[11]);
    }

    public void update() {
        super.update();
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            if (pickedElement) {
                AbstractExileCard c = (AbstractExileCard) AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                c.addModifier(element);
                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(),
                        Settings.WIDTH/2.0f, Settings.HEIGHT/2.0f));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH/2.0f, (float)Settings.HEIGHT/2.0F));
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                adp().loseGold(amount);
                imageEventText.updateBodyText(descriptions[3]);
            }
            else if (pickedSigil) {
                AbstractExileCard c = (AbstractExileCard) AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                c.sigil = true;
                c.cost = -2;
                if (c.target == AbstractCard.CardTarget.ENEMY)
                    c.target = AbstractCard.CardTarget.ALL_ENEMY;
                c.initializeDescription();
                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(),
                        Settings.WIDTH/2.0f, Settings.HEIGHT/2.0f));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH/2.0f, (float)Settings.HEIGHT/2.0F));
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                adp().loseGold(amount);
                imageEventText.updateBodyText(descriptions[4]);
            }
            imageEventText.clearAllDialogs();
            imageEventText.setDialogOption(options[12]);
            screen = CUR_SCREEN.COMPLETE;
        }
    }

    protected void buttonEffect(int buttonPressed) {
        if (screen == CUR_SCREEN.INTRO) {
            switch (buttonPressed) {
                case 0:
                    pickedElement = true;
                    screen = CUR_SCREEN.ELE_CHOICE;
                    imageEventText.updateBodyText(descriptions[1]);
                    imageEventText.clearAllDialogs();

                    if (checkForUpgradableCard(AbstractExileCard.elenum.ICE)) {
                        imageEventText.setDialogOption(options[2]);
                        LargeDialogOptionButton but = imageEventText.optionList.get(0);
                        TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, IceDamage.getPowerTips());
                    } else
                        imageEventText.setDialogOption(options[2], true);
                    if (checkForUpgradableCard(AbstractExileCard.elenum.FORCE)) {
                        imageEventText.setDialogOption(options[3]);
                        LargeDialogOptionButton but = imageEventText.optionList.get(1);
                        TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, ForceDamage.getPowerTips());
                    } else
                        imageEventText.setDialogOption(options[3], true);
                    if (checkForUpgradableCard(AbstractExileCard.elenum.DARK)) {
                        imageEventText.setDialogOption(options[4]);
                        LargeDialogOptionButton but = imageEventText.optionList.get(2);
                        TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, EldritchDamage.getPowerTips());
                    } else
                        imageEventText.setDialogOption(options[4], true);
                    if (checkForUpgradableCard(AbstractExileCard.elenum.FIRE)) {
                        imageEventText.setDialogOption(options[5]);
                        LargeDialogOptionButton but = imageEventText.optionList.get(3);
                        TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, DemonFireDamage.getPowerTips());
                    } else
                        imageEventText.setDialogOption(options[5], true);
                    if (checkForUpgradableCard(AbstractExileCard.elenum.LIGHTNING)) {
                        imageEventText.setDialogOption(options[6]);
                        LargeDialogOptionButton but = imageEventText.optionList.get(4);
                        TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, DeathLightningDamage.getPowerTips());
                    } else
                        imageEventText.setDialogOption(options[6], true);
                    break;
                case 1:
                    pickedSigil = true;
                    CardGroup sigilGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    for (AbstractCard c :  adp().masterDeck.group) {
                        if (c instanceof AbstractExileCard && c.cost >= 0)
                            sigilGroup.addToTop(c);
                    }
                    AbstractDungeon.gridSelectScreen.open(sigilGroup, 1, descriptions[2], false,
                            false, false, false);
                    for (LargeDialogOptionButton but : imageEventText.optionList)
                        TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, new ArrayList<>());
                    break;
                case 2:
                    adp().loseGold(amount);
                    adRoom().rewards.clear();
                    adRoom().rewards.add(new RewardItem(new SteelhidePotion()));
                    AbstractDungeon.combatRewardScreen.open();
                    screen = CUR_SCREEN.COMPLETE;
                    imageEventText.updateBodyText(descriptions[5]);
                    imageEventText.clearAllDialogs();
                    imageEventText.setDialogOption(options[12]);
                    break;
                case 3:
                    screen = CUR_SCREEN.COMPLETE;
                    imageEventText.updateBodyText(descriptions[6]);
                    imageEventText.clearAllDialogs();
                    imageEventText.setDialogOption(options[12]);
                    break;
            }
        } else if (screen == CUR_SCREEN.ELE_CHOICE) {
            CardGroup eleGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            if (buttonPressed == 0)
                element = AbstractExileCard.elenum.ICE;
            else if (buttonPressed == 1)
                element = AbstractExileCard.elenum.FORCE;
            else if (buttonPressed == 2)
                element = AbstractExileCard.elenum.DARK;
            else if (buttonPressed == 3)
                element = AbstractExileCard.elenum.FIRE;
            else if (buttonPressed == 4)
                element = AbstractExileCard.elenum.LIGHTNING;
            for (LargeDialogOptionButton x : imageEventText.optionList)
                TipsInDialogPatch.ButtonPreviewField.previewTips.set(x, null);
            for (AbstractCard c :  adp().masterDeck.group)
                if (c instanceof AbstractExileCard && c.type == AbstractCard.CardType.ATTACK
                        && !((AbstractExileCard) c).damageModList.contains(element))
                    eleGroup.addToTop(c);
            AbstractDungeon.gridSelectScreen.open(eleGroup, 1, descriptions[2], false);
        } else
            openMap();
    }

    private enum CUR_SCREEN {
        INTRO,
        ELE_CHOICE,
        COMPLETE;
    }

    private static int getGoldCost() {
        if (AbstractDungeon.ascensionLevel < 15)
            return GOLD_A0;
        return GOLD_A15;
    }

    private static boolean hasCardForSigil() {
        for (AbstractCard c : adp().masterDeck.group)
            if (c instanceof AbstractExileCard && c.cost >= 0)
                return true;
        return false;
    }

    private static boolean hasCardForElement() {
        for (AbstractCard card : adp().masterDeck.group)
            if (card instanceof AbstractExileCard && card.type == AbstractCard.CardType.ATTACK &&
                    ((AbstractExileCard) card).damageModList.size() < 5) {
                if (!(card instanceof Dualcast) || ((AbstractExileCard) card).damageModList.size() < 3)
                return true;
            }
        return false;
    }

    private static boolean checkForUpgradableCard(AbstractExileCard.elenum ele) {
        for (AbstractCard card : adp().masterDeck.group)
            if (card instanceof AbstractExileCard && card.type == AbstractCard.CardType.ATTACK &&
                    !((AbstractExileCard) card).damageModList.contains(ele)) {
                if (!(card instanceof Dualcast)
                        || (ele != AbstractExileCard.elenum.FORCE && ele != AbstractExileCard.elenum.ICE))
                    return true;
            }
        return false;
    }
}