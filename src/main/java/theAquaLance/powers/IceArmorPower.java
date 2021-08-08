package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;

import static theAquaLance.util.Wiz.*;

public class IceArmorPower extends AbstractTwoAmountEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("IceArmor");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public IceArmorPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        amount2 = 0;
        canGoNegative2 = false;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void update(int slot) {
        super.update(slot);
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        AbstractPower pow = adp().getPower(IntelligencePower.POWER_ID);
        if (pow != null)
            amount2 = amount * pow.amount;
        else
            amount2 = 0;
        atb(new GainBlockAction(adp(), amount2));
    }

    @Override
    public void updateDescription() {
        AbstractPower pow = adp().getPower(IntelligencePower.POWER_ID);
        if (pow != null)
            amount2 = amount * pow.amount;
        else
            amount2 = 0;
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
    }
}