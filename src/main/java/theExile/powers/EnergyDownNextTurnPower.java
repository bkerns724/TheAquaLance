package theExile.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class EnergyDownNextTurnPower extends AbstractExilePower {
    public static String POWER_ID = makeID(EnergyDownNextTurnPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EnergyDownNextTurnPower(int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, adp(), amount);
        this.name = NAME;
        priority = 6;
    }

    @Override
    public void onEnergyRecharge() {
        flash();
        adp().loseEnergy(amount);
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }
}