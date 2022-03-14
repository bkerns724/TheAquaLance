package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class ManaBurstPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID("ManaBurst");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int counter = 0;

    public ManaBurstPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        ++counter;
    }

    @Override
    public void stackPower(int stackAmount) {
        ++counter;
        super.stackPower(stackAmount);
    }

    @Override
    public void atStartOfTurn() {
        counter = amount;
    }

    @Override
    public void onDiscardSigil() {
        if (counter > 0) {
            --counter;
            atb(new GainEnergyAction(1));
        }
    }
}