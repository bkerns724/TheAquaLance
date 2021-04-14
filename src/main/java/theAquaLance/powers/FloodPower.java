package theAquaLance.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theAquaLance.AquaLanceMod;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theAquaLance.actions.AquaDrawCardAction;

import static theAquaLance.util.Wiz.*;

public class FloodPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("Flood");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean fresh = true;

    public FloodPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    public void onManualDiscard() {
        if (fresh) {
            atb(new AquaDrawCardAction(amount));
            fresh = false;
        }
    }

    @Override
    public void atStartOfTurn() {
        fresh = true;
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}