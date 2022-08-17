package theExile.powers;

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

    public ExileStudyPower(int amount) {
        super(POWER_ID, PowerType.BUFF, true, adp(), amount);
        this.name = NAME;
        ID = POWER_ID + offset++;
        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        atb(new StudyDiscoveryAction(amount));
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }
}