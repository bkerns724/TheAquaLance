package theExile.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import theExile.cards.AbstractExileCard;
import theExile.cards.EnchantedDagger;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static theExile.cards.AbstractExileCard.elenum;
import static theExile.cards.AbstractExileCard.elenum.*;
import static theExile.util.Wiz.adp;

public class WeaveAction extends AbstractGameAction {
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
            } else {
                isDone = true;
                cardsToModify.addAll(possibleCards);
                modifyCards();
            }
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

        for (AbstractCard c : cardsToModify) {
            if (c instanceof AbstractExileCard) {
                ((AbstractExileCard) c).addModifier(elementList, true);
                if (c instanceof EnchantedDagger)
                    ((EnchantedDagger) c).stableList.addAll(elementList);
                c.initializeDescription();
            }
            if (!adp().hand.group.contains(c))
                adp().hand.addToTop(c);
        }
    }
}