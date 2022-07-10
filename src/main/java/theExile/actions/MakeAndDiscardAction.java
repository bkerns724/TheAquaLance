package theExile.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class MakeAndDiscardAction extends AbstractGameAction {
    private final AbstractCard card;
    private static final float PADDING;

    public MakeAndDiscardAction(AbstractCard card) {
        UnlockTracker.markCardAsSeen(card.cardID);
        actionType = ActionType.CARD_MANIPULATION;
        this.card = card;
    }

    public void update() {
        if (card.type != CardType.CURSE && card.type != CardType.STATUS && adp().hasPower("MasterRealityPower"))
            card.upgrade();

        if (adp().hand.size() == BaseMod.MAX_HAND_SIZE)
            addToDiscard();
        else
            att(new DiscardSpecificCardAction(addToHand()));

        att(new WaitAction(0.8F));
        isDone = true;
    }

    private AbstractCard addToHand() {
        AbstractCard newCard = card.makeStatEquivalentCopy();
        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(newCard,
                (float) Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH,
                (float) Settings.HEIGHT / 2.0F));
        return newCard;
    }

    private void addToDiscard() {
        AbstractCard newCard = card.makeStatEquivalentCopy();
        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(newCard,
                (float) Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH, (float) Settings.HEIGHT / 2.0F));
    }

    static {
        PADDING = 25.0F * Settings.scale;
    }
}