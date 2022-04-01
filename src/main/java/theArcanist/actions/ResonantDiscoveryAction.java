package theArcanist.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import theArcanist.cards.AbstractArcanistCard;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.att;

public class ResonantDiscoveryAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private static final int CHOICE_AMOUNT = 3;

    public ResonantDiscoveryAction(int amount) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
    }

    public void update() {
        ArrayList<AbstractCard> generatedCards;
        generatedCards = returnTrulyRandomResonantCards();

        if (generatedCards.size() == 0)
            return;

        if (duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], false);
            tickDuration();
        } else {
            if (!retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard newCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    if (AbstractDungeon.player.hasPower("MasterRealityPower"))
                        newCard.upgrade();
                    newCard.setCostForTurn(0);
                    newCard.current_x = -1000.0F * Settings.xScale;

                    int handAmount = Math.min(amount, BaseMod.MAX_HAND_SIZE - adp().hand.size());
                    int discardAmount = amount - handAmount;
                    for (int i = 0; i < handAmount; i++) {
                        AbstractCard newCard2 = newCard.makeStatEquivalentCopy();
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(newCard2,
                                (float) Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F,
                                (float) Settings.HEIGHT / 2.0F));
                    }
                    for (int i = 0; i < discardAmount; i++) {
                        AbstractCard newCard2 = newCard.makeStatEquivalentCopy();
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(newCard2,
                                (float) Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F,
                                (float) Settings.HEIGHT / 2.0F));
                    }

                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }
                retrieveCard = true;
            }
            tickDuration();
        }
    }

    private ArrayList<AbstractCard> returnTrulyRandomResonantCards() {
        ArrayList<AbstractCard> list = new ArrayList<>();

        for (AbstractCard card : srcCommonCardPool.group)
            if (!card.hasTag(AbstractCard.CardTags.HEALING) && card instanceof AbstractArcanistCard &&
                    ((AbstractArcanistCard) card).resonant)
                list.add(card);

        for (AbstractCard card : srcUncommonCardPool.group)
            if (!card.hasTag(AbstractCard.CardTags.HEALING) && card instanceof AbstractArcanistCard &&
                    ((AbstractArcanistCard) card).resonant)
                list.add(card);

        for (AbstractCard card : srcRareCardPool.group)
            if (!card.hasTag(AbstractCard.CardTags.HEALING) && card instanceof AbstractArcanistCard &&
                    ((AbstractArcanistCard) card).resonant)
                list.add(card);

        if (list.size() <= CHOICE_AMOUNT)
            return list;

        ArrayList<AbstractCard> list2 = new ArrayList<>();

        if (AbstractDungeon.miscRng != null && adp() != null) {
            for (int i = 0; i < CHOICE_AMOUNT; i++) {
                int x = AbstractDungeon.miscRng.random(0, list.size() - 1);
                list2.add(list.get(x));
                list.remove(x);
            }
        }

        return list2;
    }
}

