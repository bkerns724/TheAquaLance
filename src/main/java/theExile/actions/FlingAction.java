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

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToEnemyTop;

public class FlingAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final int jinx;
    private final int curseJinx;
    private boolean pickedCurse = false;

    public FlingAction(AbstractMonster target, int jinx, int curseJinx) {
        this.target = target;
        this.jinx = jinx;
        this.curseJinx = curseJinx;
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
                return;
            }

            if (adp().hand.size() == 1) {
                isDone = true;
                AbstractCard c = adp().hand.getTopCard();

                adp().hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);

                if (c.color == AbstractCard.CardColor.CURSE)
                    pickedCurse =  true;

                doJinx();
                adp().hand.applyPowers();
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
            c.triggerOnManualDiscard();
            GameActionManager.incrementDiscard(false);

            if (c.color == AbstractCard.CardColor.CURSE)
                pickedCurse = true;

            doJinx();

            CardCrawlGame.dungeon.checkForPactAchievement();
            adp().hand.applyPowers();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        tickDuration();
    }

    private void doJinx() {
        if (pickedCurse)
            applyToEnemyTop(target, new JinxPower(target, curseJinx));
        else
            applyToEnemyTop(target, new JinxPower(target, jinx));
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
        TEXT = uiStrings.TEXT;
    }
}
