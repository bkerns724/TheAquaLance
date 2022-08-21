package theExile.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theExile.cards.AbstractExileCard;
import theExile.cards.Dualcast;
import theExile.powers.WeavePower;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class WeaveAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final boolean upgraded;
    private final ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();

    public WeaveAction(boolean upgraded) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (adp().hand.group.size() == 0) {
                isDone = true;
                return;
            }
            else if (adp().hand.group.size() == 1 && !upgraded) {
                isDone = true;
                cardsToExhaust.add(adp().hand.group.get(0));
                exhaustCards();
                return;
            }
            else if (!upgraded) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);
                tickDuration();
                return;
            }
            else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[1], 2, true, true, false, false);
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
            if (card instanceof Dualcast) {
                elementList.add(AbstractExileCard.elenum.ICE);
                elementList.add(AbstractExileCard.elenum.FORCE);
            }
            adp().hand.moveToExhaustPile(card);
        }

        elementList = (ArrayList<AbstractExileCard.elenum>) elementList.stream().distinct().collect(Collectors.toList());

        if (!elementList.isEmpty())
            applyToSelf(new WeavePower(elementList));
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID("WeaveAction"));
        TEXT = uiStrings.TEXT;
    }
}