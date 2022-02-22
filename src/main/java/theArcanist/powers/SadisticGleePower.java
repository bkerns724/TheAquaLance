package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import theArcanist.ArcanistMod;


import static theArcanist.util.Wiz.*;

public class SadisticGleePower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID("SadisticGlee");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SadisticGleePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == PowerType.DEBUFF && source == owner && target != owner
                && !power.ID.equals(GainStrengthPower.POWER_ID))
            att(new GainBlockAction(owner, amount, true));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}