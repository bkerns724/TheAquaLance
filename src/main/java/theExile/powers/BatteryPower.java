package theExile.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.ExileMod;
import theExile.cards.AbstractExileCard;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class BatteryPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(BatteryPower.class.getSimpleName());

    public BatteryPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractExileCard && ((AbstractExileCard) card).damageModList.contains(AbstractExileCard.elenum.LIGHTNING))
            applyToSelf(new ChargePower(amount));
    }
}