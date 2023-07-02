package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.JinxPower;

import static theExile.util.Wiz.*;

public class SpreadCurseAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final int extraHits;
    private final int jinxAmount;
    private boolean pickedCurse = false;

    public SpreadCurseAction(int extraHits, int jinxAmount, AbstractMonster target) {
        this.target = target;
        this.extraHits = extraHits;
        this.jinxAmount = jinxAmount;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.DISCARD;
    }

    @Override
    public void update() {
        if (target == null) {
            isDone = true;
            return;
        }
        if (duration == startDuration) {
            if (adp().hand.size() == 0) {
                isDone = true;
                applyJinx();
                return;
            }

            if (adp().hand.size() == 1) {
                isDone = true;
                AbstractCard c = adp().hand.getTopCard();

                adp().hand.moveToDiscardPile(c);
                GameActionManager.incrementDiscard(false);
                adp().hand.applyPowers();

                if (c.color == AbstractCard.CardColor.CURSE)
                    pickedCurse =  true;
                applyJinx();
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            if (AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                isDone = true;
                return;
            }
            AbstractCard c = AbstractDungeon.handCardSelectScreen.selectedCards.getTopCard();
            adp().hand.moveToDiscardPile(c);
            GameActionManager.incrementDiscard(false);
            adp().hand.applyPowers();

            if (c.color == AbstractCard.CardColor.CURSE)
                pickedCurse = true;
            applyJinx();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        tickDuration();
    }

    private void applyJinx() {
        int count = 1 + extraHits * (pickedCurse ? 1 : 0);
        for (int i = 0; i < count; i++)
            applyToEnemyTop(target, new JinxPower(target, jinxAmount));
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
        TEXT = uiStrings.TEXT;
    }
}
