package theArcanist.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.Sozu;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import theArcanist.ArcanistMod;
import theArcanist.TheArcanist;
import theArcanist.patches.TipsInDialogPatch;
import theArcanist.potions.SlipperyPotion;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;

public class StrangeGarden extends AbstractArcanistEvent {
    public static final String ID = makeID(StrangeGarden.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.NORMAL;
    private static final float HEAL_AMOUNT = 0.1f;

    private AbstractCard commonCard = null;
    private AbstractCard uncommonCard = null;
    private int healAmount;

    private CUR_SCREEN screen = CUR_SCREEN.INTRO;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ArcanistMod.makeImagePath("events/" + StrangeGarden.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = StrangeGarden.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.spawnCondition = () -> (adp().chosenClass == TheArcanist.Enums.THE_ARCANIST || ArcanistMod.isUniversalEvents());
        params.bonusCondition = () -> (hasCardOfRarity(AbstractCard.CardRarity.COMMON) ||
                (hasCardOfRarity(AbstractCard.CardRarity.UNCOMMON) && !adp().hasRelic(Sozu.ID)));
        return params;
    }

    public StrangeGarden() {
        super(eventStrings, IMAGE_PATH, getHealAmount());
        healAmount = amount;

        SlipperyPotion pot = new SlipperyPotion();

        if (hasCardOfRarity(AbstractCard.CardRarity.UNCOMMON)) {
            AbstractCard card = getCardOfRarity(AbstractCard.CardRarity.UNCOMMON);
            if (card != null && !adp().hasRelic(Sozu.ID)) {
                uncommonCard = card;
                String opt = options[0].replace("!Card!",
                        FontHelper.colorString(card.name, "r"));
                opt = opt.replace("!PotionString!", FontHelper.colorString(pot.name, "g"));
                imageEventText.setDialogOption(opt, card.makeStatEquivalentCopy());
                LargeDialogOptionButton but = imageEventText.optionList.get(0);
                TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, pot.tips);
            }
        }
        else
            imageEventText.setDialogOption(options[1], true);

        if (hasCardOfRarity(AbstractCard.CardRarity.COMMON)) {
            AbstractCard card = getCardOfRarity(AbstractCard.CardRarity.COMMON);
            if (card != null) {
                commonCard = card;
                imageEventText.setDialogOption(options[2].replace("!Card!",
                        FontHelper.colorString(card.name, "r")), card.makeStatEquivalentCopy());
            }
        }
        else
            imageEventText.setDialogOption(options[3], true);

        imageEventText.setDialogOption(options[4]);
    }

    protected void buttonEffect(int buttonPressed) {
        if (screen == CUR_SCREEN.INTRO) {
            screen = CUR_SCREEN.COMPLETE;
            switch (buttonPressed) {
                case 0:
                    AbstractDungeon.effectList.add(new PurgeCardEffect(uncommonCard));
                    AbstractDungeon.player.masterDeck.removeCard(uncommonCard);
                    AbstractDungeon.getCurrRoom().rewards.clear();
                    AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(new SlipperyPotion()));
                    AbstractDungeon.combatRewardScreen.open();
                    imageEventText.updateBodyText(descriptions[1]);
                    break;
                case 1:
                    AbstractDungeon.effectList.add(new PurgeCardEffect(commonCard));
                    AbstractDungeon.player.masterDeck.removeCard(commonCard);
                    adp().heal(healAmount);
                    imageEventText.updateBodyText(descriptions[2]);
                    break;
                case 2:
                    imageEventText.updateBodyText(descriptions[3]);
                    break;
            }
            imageEventText.clearAllDialogs();
            imageEventText.setDialogOption(options[5]);
        } else
            openMap();
    }

    private enum CUR_SCREEN {
        INTRO,
        COMPLETE;
    }

    private static boolean hasCardOfRarity(AbstractCard.CardRarity rarity) {
        for (AbstractCard card : CardGroup.getGroupWithoutBottledCards(adp().masterDeck).group) {
            if (card.rarity == rarity)
                return true;
        }
        return false;
    }

    private static AbstractCard getCardOfRarity(AbstractCard.CardRarity rarity) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        for (AbstractCard card : CardGroup.getGroupWithoutBottledCards(adp().masterDeck).group) {
            if (card.rarity == rarity)
                list.add(card);
        }
        if (list.size() == 0)
            return null;
        int x = AbstractDungeon.eventRng.random(0, list.size() - 1);
        return list.get(x);
    }

    private static int getHealAmount() {
        return Math.max((int)(HEAL_AMOUNT*adp().maxHealth), 1);
    }
}