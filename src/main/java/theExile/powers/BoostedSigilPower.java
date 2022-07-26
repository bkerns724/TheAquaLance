package theExile.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.ExileMod;

public class BoostedSigilPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(BoostedSigilPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int counter = 0;

    public BoostedSigilPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        counter = amount;
    }

    @Override
    public void atStartOfTurn() {
        counter = amount;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        counter += stackAmount;
    }
}