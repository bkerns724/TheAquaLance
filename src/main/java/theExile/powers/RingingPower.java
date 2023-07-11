package theExile.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

// Code in AbstractExileCard
public class RingingPower extends AbstractExilePower {
    public static String POWER_ID = makeID(RingingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RingingPower(int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, adp(), amount);
        this.name = NAME;
    }
}