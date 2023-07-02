package theExile.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import theExile.cards.AbstractExileCard;
import theExile.cards.EnchantedDagger;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static theExile.cards.AbstractExileCard.elenum;
import static theExile.util.Wiz.adp;

public class WeaveAction extends AbstractGameAction {
    private final ArrayList<AbstractCard> cardsToModify = new ArrayList<>();

    public WeaveAction() {
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractExileCard> possibleCards = new ArrayList<>();
            for (AbstractCard c : adp().hand.group) {
                if (c instanceof AbstractExileCard && c.type == AbstractCard.CardType.ATTACK)
                    possibleCards.add((AbstractExileCard) c);
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

        elementList = (ArrayList<elenum>) elementList.stream().distinct().collect(Collectors.toList());

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