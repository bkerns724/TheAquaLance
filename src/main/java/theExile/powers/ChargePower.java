package theExile.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class ChargePower extends AbstractExilePower {
    public static String POWER_ID = makeID(ChargePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ChargePower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        applyToSelf(new VigorPower(adp(), amount));
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }
}