package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theExile.ExileMod;
import theExile.cards.AbstractExileCard;
import theExile.cards.AbstractResonantCard;
import theExile.cards.cardUtil.Resonance;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class ConvertAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractExileCard cardToModify;
    private final ArrayList<AbstractCard> ineligibleCards = new ArrayList<>();

    public ConvertAction() {
        duration = startDuration = Settings.ACTION_DUR_FAST;
        amount = 1;
    }

    @Override
    public void update() {
        if (duration == startDuration) {
            ArrayList<AbstractExileCard> possibleCards = new ArrayList<>();
            for (AbstractCard c : adp().hand.group) {
                if (c instanceof AbstractExileCard && c.type != AbstractCard.CardType.POWER
                        && !c.exhaust && !(c instanceof AbstractResonantCard))
                    possibleCards.add((AbstractExileCard) c);
                else
                    ineligibleCards.add(c);
            }

            ExileMod.logger.info("Here we go");
            for (AbstractCard c : adp().hand.group)
                ExileMod.logger.info(c.name);
            ExileMod.logger.info("Possible");
            for (AbstractCard c : possibleCards)
                ExileMod.logger.info(c.name);
            ExileMod.logger.info("Ineligible");
            for (AbstractCard c : ineligibleCards)
                ExileMod.logger.info(c.name);

            if (possibleCards.size() <= 0) {
                ExileMod.logger.info("Size <= 0");
                isDone = true;
                return;
            }

            if (possibleCards.size() <= amount) {
                ExileMod.logger.info("size <= amount");
                ExileMod.logger.info(amount);
                isDone = true;
                cardToModify = possibleCards.get(0);
                modifyCard();
                return;
            }

            adp().hand.group.removeAll(ineligibleCards);
            ExileMod.logger.info("Hand cards");
            for (AbstractCard c : adp().hand.group)
                ExileMod.logger.info(c.name);
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], amount, false, false,
                    false, false);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            ExileMod.logger.info("Selected Cards");
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group)
                ExileMod.logger.info(c.name);
            if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty())
                cardToModify = (AbstractExileCard) AbstractDungeon.handCardSelectScreen.selectedCards.group.get(0);
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            modifyCard();
            returnCards();
            isDone = true;
        }

        tickDuration();
    }

    private void modifyCard() {
        ExileMod.logger.info("Modify Card");
        ExileMod.logger.info(cardToModify.name);

        AbstractDungeon.actionManager.addToTop(new ShowCardAction(cardToModify));
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));

        Resonance res = new Resonance();
        res.cards.add((AbstractExileCard) cardToModify.makeStatEquivalentCopy());
        res.toPower();

        AbstractDungeon.player.hand.empower(cardToModify);
        AbstractDungeon.player.hand.applyPowers();
        AbstractDungeon.player.hand.glowCheck();
    }

    private void returnCards() {
        ExileMod.logger.info("Returning Cards");
        for (AbstractCard c : ineligibleCards) {
            ExileMod.logger.info(c.name);
            if (!adp().hand.group.contains(c))
                adp().hand.addToTop(c);
        }

        adp().hand.refreshHandLayout();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID("ExileStudy"));
        TEXT = uiStrings.TEXT;
    }
}
