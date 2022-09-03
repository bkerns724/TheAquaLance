package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.GoldenEye;

import static theExile.util.Wiz.adp;

public class DivinationAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public DivinationAction(int scryAmount) {
        amount = scryAmount;
        if (adp().hasRelic(GoldenEye.ID)) {
            adp().getRelic(GoldenEye.ID).flash();
            amount += 2;
        }

        actionType = ActionType.CARD_MANIPULATION;
        startDuration = duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead())
            isDone = true;
        else {
            if (duration == startDuration) {
                for (AbstractPower power : adp().powers)
                    power.onScry();

                if (adp().drawPile.isEmpty()) {
                    isDone = true;
                    return;
                }

                CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                for(int i = 0; i < Math.min(amount, adp().drawPile.size()); ++i)
                    tmpGroup.addToTop(adp().drawPile.group.get(adp().drawPile.size() - i - 1));

                AbstractDungeon.gridSelectScreen.open(tmpGroup, amount, true, TEXT[0]);

                for (AbstractCard c : tmpGroup.group)
                    c.upgrade();
            } else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
                    adp().drawPile.moveToDiscardPile(c);

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }

            for (AbstractCard c : adp().discardPile.group)
                c.triggerOnScry();

            tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ReprogramAction");
        TEXT = uiStrings.TEXT;
    }
}
