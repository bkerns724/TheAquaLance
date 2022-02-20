package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;
import theArcanist.actions.ChaosAuraAction;

import static theArcanist.util.Wiz.*;

public class ChaosAuraPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID("ChaosAura");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ChaosAuraPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void atStartOfTurn() {
        forAllMonstersLiving(m -> atb(new ChaosAuraAction(m, amount)));
    }

    @Override
    public void updateDescription() {
        if (amount % 3 == 0)
            description = DESCRIPTIONS[0] + amount/3 + DESCRIPTIONS[3];
        else
            description = DESCRIPTIONS[0] + amount/3 +DESCRIPTIONS[1] + amount%3 + DESCRIPTIONS[2] + DESCRIPTIONS[3];

    }
}