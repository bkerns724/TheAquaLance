package theExile.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.cards.AbstractResonantCard;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class ResonantCostZeroPower extends AbstractExilePower {
    public static String POWER_ID = makeID(ResonantCostZeroPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ResonantCostZeroPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractResonantCard && !card.purgeOnUse && amount > 0) {
            flash();
            --amount;
            if (amount == 0)
                addToTop(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }
}