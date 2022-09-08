package theExile.actions;

import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.cards.AbstractExileCard;

import static theExile.util.Wiz.*;

public class VoodooDollAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final AbstractExileCard card;
    private final int multiplier;

    public VoodooDollAction(int multiplier, AbstractExileCard card, AbstractMonster target) {
        this.target = target;
        this.card = card;
        this.multiplier = multiplier;
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
                    card.baseDamage *= multiplier;
                doDamage();
                if (c.color == AbstractCard.CardColor.CURSE)
                    card.baseDamage /= multiplier;

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
                card.baseDamage *= multiplier;
            doDamage();
            if (c.color == AbstractCard.CardColor.CURSE)
                card.baseDamage /= multiplier;

            CardCrawlGame.dungeon.checkForPactAchievement();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        tickDuration();
    }

    private void doDamage() {
        card.calculateCardDamage((AbstractMonster) target);
        DamageModContainer container = new DamageModContainer(card, DamageModifierManager.modifiers(card));
        DamageInfo info = BindingHelper.makeInfo(container, adp(), card.damage, DamageInfo.DamageType.NORMAL);
        if (card.damageModList.isEmpty())
            att(new AttackAction((AbstractMonster) target, info, getSlashEffect(card.damage)));
        else
            att(new AttackAction((AbstractMonster) target, info, getAttackEffect(card.damage, card.damageModList, false)));
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
        TEXT = uiStrings.TEXT;
    }
}
