package theExile.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.ExileMod;
import theExile.actions.StudyDiscoveryAction;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class ExileStudyPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(ExileStudyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int offset = 0;

    public ExileStudyPower(int amount, int amount2) {
        super(POWER_ID, PowerType.BUFF, true, adp(), amount);
        this.name = NAME;
        ID = POWER_ID + offset++;
        isTwoAmount = true;
        this.amount2 = amount2;
        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (amount > 1)
            atb(new ReducePowerAction(owner, owner, this, 1));
        else {
            atb(new StudyDiscoveryAction(amount2));
            atb(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }
}