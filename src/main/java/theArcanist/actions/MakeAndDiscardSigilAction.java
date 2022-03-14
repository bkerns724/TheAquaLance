package theArcanist.actions;

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
import theArcanist.cards.AbstractArcanistCard;

import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.att;

public class MakeAndDiscardSigilAction extends AbstractGameAction {
    private final AbstractArcanistCard c;
    private static final float PADDING;
    private final boolean sameUUID;

    public MakeAndDiscardSigilAction(AbstractArcanistCard card) {
        sameUUID = false;
        UnlockTracker.markCardAsSeen(card.cardID);
        amount = 1;
        actionType = ActionType.CARD_MANIPULATION;
        c = card;
        if (c.type != CardType.CURSE && c.type != CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower")) {
            c.upgrade();
        }
    }

    public void update() {
        if (amount == 0) {
            isDone = true;
        } else {
            int discardAmount = 0;
            int handAmount = amount;
            if (amount + adp().hand.size() > BaseMod.MAX_HAND_SIZE) {
                adp().createHandIsFullDialog();
                discardAmount = amount + adp().hand.size() - BaseMod.MAX_HAND_SIZE;
                handAmount -= discardAmount;
            }

            AbstractArcanistCard newCard = addToHand(handAmount);
            addToDiscard(discardAmount);

            if (newCard != null)
                att(new DiscardSpecificCardAction(newCard));

            att(new WaitAction(0.8F));

            isDone = true;
        }
    }

    private AbstractArcanistCard addToHand(int handAmt) {
        if (handAmt == 1) {
            AbstractArcanistCard newCard = makeNewCard();
            AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(newCard,
                    (float) Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH),
                    (float) Settings.HEIGHT / 2.0F));
            return newCard;
        }
        return null;
    }

    private void addToDiscard(int discardAmt) {
        if (discardAmt == 1)
            AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard(),
                    (float) Settings.WIDTH / 2.0F + PADDING + AbstractCard.IMG_WIDTH, (float) Settings.HEIGHT / 2.0F));
    }

    private AbstractArcanistCard makeNewCard() {
        return (AbstractArcanistCard) (sameUUID ? c.makeSameInstanceOf() : c.makeStatEquivalentCopy());
    }

    static {
        PADDING = 25.0F * Settings.scale;
    }
}