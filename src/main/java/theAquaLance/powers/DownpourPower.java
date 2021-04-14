package theAquaLance.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theAquaLance.actions.AquaGainEnergyAction;

import static theAquaLance.util.Wiz.*;

public class DownpourPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("Downpour");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int DEBUFF_COUNT = 3;
    private int debuffsLeft = DEBUFF_COUNT;

    public DownpourPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == owner && power.type == PowerType.DEBUFF) {
            debuffsLeft--;
            if (debuffsLeft == 0)
                atb(new AquaGainEnergyAction(amount));
        }
    }

    @Override
    public void atStartOfTurn() {
        debuffsLeft = DEBUFF_COUNT;
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}