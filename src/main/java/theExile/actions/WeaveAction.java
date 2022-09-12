package theExile.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theExile.cards.AbstractExileCard;
import theExile.cards.EnchantedDagger;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum;
import static theExile.cards.AbstractExileCard.elenum.*;
import static theExile.util.Wiz.adp;

public class WeaveAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final ArrayList<AbstractCard> cardsToModify = new ArrayList<>();
    private final ArrayList<AbstractCard> ineligibleCards = new ArrayList<>();

    public WeaveAction(int amount) {
        this.amount = amount;
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractExileCard> possibleCards = new ArrayList<>();
            for (AbstractCard c : adp().hand.group) {
                if (c instanceof AbstractExileCard && c.type == AbstractCard.CardType.ATTACK)
                    possibleCards.add((AbstractExileCard) c);
                else
                    ineligibleCards.add(c);
            }


            if (possibleCards.size() <= 0) {
                isDone = true;
                return;
            }
            if (possibleCards.size() <= amount) {
                isDone = true;
                cardsToModify.addAll(possibleCards);
                modifyCards();
            }
            else {
                adp().hand.group.removeAll(ineligibleCards);
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 2, false, false,
                        false, false);
                tickDuration();
                return;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            cardsToModify.addAll(AbstractDungeon.handCardSelectScreen.selectedCards.group);
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            modifyCards();
            returnCards();
            isDone = true;
        }

        tickDuration();
    }

    private void modifyCards() {
        ArrayList<AbstractExileCard.elenum> elementList = new ArrayList<>();
        for (AbstractCard c : cardsToModify)
            if (c instanceof AbstractExileCard)
                elementList.addAll(((AbstractExileCard) c).damageModList);

        if (elementList.contains(FAKE_ICE))
            elementList.add(ICE);
        if (elementList.contains(FAKE_PHANTASMAL))
            elementList.add(PHANTASMAL);
        if (elementList.contains(FAKE_ELDRITCH))
            elementList.add(ELDRITCH);
        if (elementList.contains(FAKE_LIGHTNING))
            elementList.add(LIGHTNING);

        elementList = (ArrayList<elenum>) elementList.stream().distinct().collect(Collectors.toList());

        elementList.remove(FAKE_ICE);
        elementList.remove(FAKE_PHANTASMAL);
        elementList.remove(FAKE_LIGHTNING);
        elementList.remove(FAKE_ELDRITCH);

        for (AbstractCard c : cardsToModify) {
            if (c instanceof AbstractExileCard) {
                ((AbstractExileCard) c).addModifier(elementList, true);
                if (c instanceof EnchantedDagger)
                    ((EnchantedDagger) c).addModifier(elementList, true);
                c.initializeDescription();
            }
            if (!adp().hand.group.contains(c))
                adp().hand.addToTop(c);
        }
    }

    private void returnCards() {
        for (AbstractCard c : ineligibleCards)
            if (!adp().hand.group.contains(c))
                adp().hand.addToTop(c);

        adp().hand.refreshHandLayout();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID("WeaveAction"));
        TEXT = uiStrings.TEXT;
    }
}