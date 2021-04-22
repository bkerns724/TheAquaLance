package theAquaLance.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theAquaLance.actions.AquaGainEnergyAction;

import static theAquaLance.util.Wiz.*;

public class IceStormPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("IceStorm");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int timesUsed = 0;

    public IceStormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == owner && power instanceof EmbedPower && timesUsed < amount) {
            atb(new AquaGainEnergyAction(1));
            timesUsed++;
        }
    }

    @Override
    public void atStartOfTurn() {
        timesUsed = 0;
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}