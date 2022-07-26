package theExile.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.ExileMod;

import static theExile.util.Wiz.atb;

public class MomentumPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(MomentumPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int counter = 0;

    public MomentumPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        counter += amount;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        counter += stackAmount;
    }

    @Override
    public void atStartOfTurn() {
        counter = amount;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && counter > 0) {
            counter--;
            atb(new DrawCardAction(1));
        }
    }
}