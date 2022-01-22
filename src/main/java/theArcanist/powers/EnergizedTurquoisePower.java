package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class EnergizedTurquoisePower extends AbstractEasyPower {
    public static final String POWER_ID = ArcanistMod.makeID("EnergizedTurquoise");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EnergizedTurquoisePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void onEnergyRecharge() {
        flash();
        adp().gainEnergy(amount);
        atb(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}