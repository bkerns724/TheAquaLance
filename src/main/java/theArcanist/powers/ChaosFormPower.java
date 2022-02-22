package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;
import theArcanist.actions.ChaosFormAction;


import static theArcanist.util.Wiz.*;

public class ChaosFormPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID("ChaosForm");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ChaosFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void atStartOfTurn() {
        atb(new ChaosFormAction(amount));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}