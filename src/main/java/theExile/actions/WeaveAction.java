package theExile.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theExile.cards.AbstractExileCard;
import theExile.powers.WeavePower;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.*;
import static theExile.cards.AbstractExileCard.elenum;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class WeaveAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();

    public WeaveAction() {
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (adp().hand.group.size() == 0) {
                isDone = true;
                return;
            }
            else if (adp().hand.group.size() == 1) {
                isDone = true;
                cardsToExhaust.add(adp().hand.group.get(0));
                exhaustCards();
                return;
            }
            else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);
                tickDuration();
                return;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            cardsToExhaust.addAll(AbstractDungeon.handCardSelectScreen.selectedCards.group);
            exhaustCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            isDone = true;
        }

        tickDuration();
    }

    private void exhaustCards() {
        ArrayList<AbstractExileCard.elenum> elementList = new ArrayList<>();
        for (AbstractCard card : cardsToExhaust) {
            if (card instanceof AbstractExileCard)
                elementList.addAll(((AbstractExileCard) card).damageModList);
            adp().hand.moveToExhaustPile(card);
            CardCrawlGame.dungeon.checkForPactAchievement();
        }

        if (elementList.contains(FAKE_ICE))
            elementList.add(ICE);
        if (elementList.contains(FAKE_FORCE))
            elementList.add(FORCE);
        if (elementList.contains(FAKE_ELDRITCH))
            elementList.add(ELDRITCH);
        if (elementList.contains(FAKE_LIGHTNING))
            elementList.add(LIGHTNING);

        elementList = (ArrayList<elenum>) elementList.stream().distinct().collect(Collectors.toList());

        elementList.remove(FAKE_ICE);
        elementList.remove(FAKE_FORCE);
        elementList.remove(FAKE_LIGHTNING);
        elementList.remove(FAKE_ELDRITCH);

        if (!elementList.isEmpty())
            applyToSelf(new WeavePower(elementList));
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID("WeaveAction"));
        TEXT = uiStrings.TEXT;
    }
}