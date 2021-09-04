package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.cards.AbstractEmbedCard;

import java.util.Iterator;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.adp;

public class PlayDiscardShardAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public PlayDiscardShardAction(AbstractMonster m) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_MED;
        target = m;
    }

    public void update() {
        CardGroup cards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        if (duration == Settings.ACTION_DUR_MED) {
            for (AbstractCard c : adp().discardPile.group) {
                if (c instanceof AbstractEmbedCard)
                    cards.addToRandomSpot(c);
            }

            AbstractCard chosenCard = null;
            if (cards.size() == 0) {
                isDone = true;
                return;
            }
            else if (cards.size() == 1)
                chosenCard = cards.getTopCard();
            else
                chosenCard = cards.getRandomCard(AbstractDungeon.cardRandomRng);

            if (chosenCard != null) { // Should never be null
                adp().discardPile.group.remove(chosenCard);
                AbstractDungeon.getCurrRoom().souls.remove(chosenCard);
                AbstractDungeon.player.limbo.group.add(chosenCard);
                chosenCard.current_y = -200.0F * Settings.scale;
                chosenCard.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                chosenCard.target_y = (float)Settings.HEIGHT / 2.0F;
                chosenCard.targetAngle = 0.0F;
                chosenCard.lighten(false);
                chosenCard.drawScale = 0.12F;
                chosenCard.targetDrawScale = 0.75F;
                chosenCard.applyPowers();
                this.addToTop(new NewQueueCardAction(chosenCard, target, false, true));
                this.addToTop(new UnlimboAction(chosenCard));
                if (!Settings.FAST_MODE) {
                    this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                } else {
                    this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
                }
            }

            isDone = true;
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID("FetchShardAction"));
        TEXT = uiStrings.TEXT;
    }
}