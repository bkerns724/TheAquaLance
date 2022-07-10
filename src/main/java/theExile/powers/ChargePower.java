package theExile.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.ExileMod;

import static theExile.util.Wiz.*;

public class ChargePower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(ChargePower.class.getSimpleName());

    public ChargePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (EnergyPanel.getCurrentEnergy() > 0)
            applyToSelf(new VigorPower(adp(), amount*EnergyPanel.getCurrentEnergy()));
    }
}