package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class VoodooDollAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final int extraHits;
    private boolean pickedCurse = false;
    private final AttackEffect effect;
    private final DamageInfo info;

    public VoodooDollAction(int extraHits, DamageInfo info, AbstractMonster target, AttackEffect effect) {
        this.target = target;
        this.extraHits = extraHits;
        this.effect = effect;
        this.info = info;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.EXHAUST;
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
                doDamage();
                return;
            }

            if (adp().hand.size() == 1) {
                isDone = true;
                AbstractCard c = adp().hand.getTopCard();
                adp().hand.moveToExhaustPile(c);
                if (c.color == AbstractCard.CardColor.CURSE)
                    pickedCurse =  true;

                doDamage();

                CardCrawlGame.dungeon.checkForPactAchievement();
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
            adp().hand.moveToExhaustPile(c);
            if (c.color == AbstractCard.CardColor.CURSE)
                pickedCurse = true;
            doDamage();

            CardCrawlGame.dungeon.checkForPactAchievement();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        tickDuration();
    }

    private void doDamage() {
        int count = 1 + extraHits * (pickedCurse ? 1 : 0);
        for (int i = 0; i < count; i++)
            att(new AttackAction((AbstractMonster) target, info, effect));
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
        TEXT = uiStrings.TEXT;
    }
}
