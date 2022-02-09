package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class DrawNextTurnPower extends AbstractArcanistPower {
    public static final String POWER_ID = ArcanistMod.makeID("DrawNextTurn");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DrawNextTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        priority = 6;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (!adp().hasPower(SpeedPower.POWER_ID))
            atb(new DrawCardAction(amount));
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }

    @Override
    public void upDescription() {
        description = DESCRIPTIONS[0];
    }
}