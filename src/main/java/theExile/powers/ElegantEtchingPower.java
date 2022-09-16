package theExile.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.cards.AbstractExileCard;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class ElegantEtchingPower extends AbstractExilePower {
    public static String POWER_ID = makeID(ElegantEtchingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ElegantEtchingPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
        amount2 = amount + 1;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        amount2 = amount + 1;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card instanceof AbstractExileCard && ((AbstractExileCard) card).sigil) {
            action.exhaustCard = true;
            for (int i = 0; i < amount; i++) {
                flash();
                AbstractMonster m = null;
                if (action.target != null)
                    m = (AbstractMonster) action.target;

                AbstractCard tmp = card.makeSameInstanceOf();
                ((AbstractExileCard) tmp).beingDiscarded = true;
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.current_x = card.current_x;
                tmp.current_y = card.current_y;
                tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                tmp.target_y = (float) Settings.HEIGHT / 2.0F;
                if (m != null)
                    tmp.calculateCardDamage(m);

                tmp.purgeOnUse = true;
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
            }
        }
    }

    @Override
    public void updateDescription() {
        amount2 = amount + 1;
        super.updateDescription();
    }
}