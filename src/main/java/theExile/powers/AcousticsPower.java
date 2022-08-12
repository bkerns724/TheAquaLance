package theExile.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class AcousticsPower extends AbstractExilePower {
    // Code in Resonance.java
    public static String POWER_ID = makeID(AcousticsPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AcousticsPower() {
        super(POWER_ID, PowerType.BUFF, false, adp(), -1);
        this.name = NAME;
    }
}