package theExile.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.cards.AbstractExileCard;
import theExile.relics.ManaCrystal;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.PHANTASMAL;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class ForceChargePower extends AbstractExilePower {
    public static String POWER_ID = makeID(ForceChargePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final float BONUS = 0.5f;

    public ForceChargePower() {
        super(POWER_ID, PowerType.BUFF, false, adp(), 1);
        canGoNegative = false;
        this.name = NAME;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (adp() != null && adp().hasRelic(ManaCrystal.ID)) {
            if (amount > ManaCrystal.NEW_LIMIT)
                amount = ManaCrystal.NEW_LIMIT;
        }
        else if (amount > 1)
            amount = 1;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card instanceof AbstractExileCard && ((AbstractExileCard) card).damageModList.contains(PHANTASMAL)) {

            float bonus = amount * BONUS;

            AbstractPower prowessPow = adp().getPower(ElementalProwessForce.POWER_ID);
            if (prowessPow != null)
                bonus *= (1 + prowessPow.amount);

            return damage * (1f + bonus);
        }
        return damage;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractExileCard && ((AbstractExileCard) card).damageModList.contains(PHANTASMAL)) {
            flash();
            atb(new RemoveSpecificPowerAction(adp(), adp(), this));
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