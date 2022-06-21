package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class BlessedNecklaceAction extends AbstractGameAction {
    private int blockAmount;
    private static final String uiStringsKey = ArcanistMod.makeID(BlessedNecklaceAction.class.getSimpleName());
    private static final UIStrings uiStrings;
    private static final String[] TEXT;

    public BlessedNecklaceAction(int blockAmount) {
        this.blockAmount = blockAmount;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);

            atb(new WaitAction(0.25F));
            tickDuration();
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                    att(new GainBlockAction(adp(),
                            blockAmount*AbstractDungeon.handCardSelectScreen.selectedCards.group.size()));

                    for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                        adp().hand.moveToDiscardPile(c);
                        GameActionManager.incrementDiscard(false);
                        c.triggerOnManualDiscard();
                    }
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }
            tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(uiStringsKey);
        TEXT = uiStrings.TEXT;
    }
}
