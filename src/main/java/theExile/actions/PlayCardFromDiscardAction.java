package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import theExile.cards.AbstractExileCard;

public class PlayCardFromDiscardAction extends AbstractGameAction {
    private AbstractCard card;

    public PlayCardFromDiscardAction(AbstractCard card) {
        this.card = card;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (card instanceof AbstractExileCard) {
            AbstractExileCard cardExile = (AbstractExileCard) card;
            cardExile.beingDiscarded = true;
            cardExile.triggerOnManualDiscard();
        }
        isDone = true;
    }
}
