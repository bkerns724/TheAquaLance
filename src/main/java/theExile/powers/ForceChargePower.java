package theExile.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.cards.AbstractExileCard;
import theExile.cards.AbstractResonantCard;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.FORCE;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class ForceChargePower extends AbstractExilePower {
    public static String POWER_ID = makeID(ForceChargePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final float BONUS = 0.5f;

    public ForceChargePower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        canGoNegative = false;
        this.name = NAME;
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractExileCard && ((AbstractExileCard) card).damageModList.contains(FORCE)) {
            flash();
            atb(new RemoveSpecificPowerAction(adp(), adp(), this));
        } else if (card instanceof AbstractResonantCard) {
            for (AbstractExileCard c : ((AbstractResonantCard) card).resonance.cards) {
                if (c.damageModList.contains(FORCE)) {
                    flash();
                    atb(new RemoveSpecificPowerAction(adp(), adp(), this));
                    return;
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = descriptionArray[0];
        else if (amount == 2)
            description = descriptionArray[1];
        else
            description = descriptionArray[2];
    }
}