package theExile.powers;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class ManaStormPower extends AbstractExilePower {
    public static String POWER_ID = makeID(ManaStormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ManaStormPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
        priority = 7;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        int count = amount;
        if (adp().hasPower(DiscardNextTurnPower.POWER_ID))
            count += adp().getPower(DiscardNextTurnPower.POWER_ID).amount;
        atb(new DiscardAction(adp(), adp(), count, false));
        atb(new GainEnergyAction(amount));
    }
}