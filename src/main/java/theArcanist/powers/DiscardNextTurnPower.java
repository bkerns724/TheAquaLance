package theArcanist.powers;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static theArcanist.util.Wiz.*;

public class DiscardNextTurnPower extends AbstractArcanistPower {
    public static final String POWER_ID = ArcanistMod.makeID("DiscardNextTurn");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DiscardNextTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        priority = 7;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (!adp().hasPower(SpeedPower.POWER_ID))
            atb(new DiscardAction(adp(), adp(), amount, false));
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }
}