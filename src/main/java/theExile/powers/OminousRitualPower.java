package theExile.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.forAllMonstersLiving;

public class OminousRitualPower extends AbstractExilePower {
    public static String POWER_ID = makeID(OminousRitualPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public OminousRitualPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new CorrodedPower(mon, amount)));
    }
}