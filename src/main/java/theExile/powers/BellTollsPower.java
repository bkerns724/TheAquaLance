package theExile.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.actions.BellLoseHPAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class BellTollsPower extends AbstractExilePower {
    public static String POWER_ID = makeID(BellTollsPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BellTollsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
        amount2 = amount;
        isTwoAmount = true;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        amount2 += stackAmount;
    }

    @Override
    public void atStartOfTurn() {
        atb(new BellLoseHPAction(owner, amount2));
        amount2 += amount;
        updateDescription();
    }
}