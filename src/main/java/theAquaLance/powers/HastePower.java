package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import theAquaLance.AquaLanceMod;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theAquaLance.util.Wiz.*;

public class HastePower extends AbstractEasyPower {
    public static final String POWER_ID = AquaLanceMod.makeID("Haste");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HastePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, true, owner, amount);
        this.name = NAME;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        adp().gainEnergy(1);
        atb(new DrawCardAction(1));
        if (amount == 1)
            atb(new RemoveSpecificPowerAction(owner, owner, this));
        else
            atb(new ReducePowerAction(owner, owner, POWER_ID, 1));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}