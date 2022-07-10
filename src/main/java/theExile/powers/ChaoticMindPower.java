package theExile.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;
import theExile.actions.RapidsAction;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class ChaoticMindPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(ChaoticMindPower.class.getSimpleName());

    public ChaoticMindPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        atb(new RapidsAction(amount));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }
}