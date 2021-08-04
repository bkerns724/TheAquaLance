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
        amount2 = 1;
        canGoNegative2 = false;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        amount2++;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        int blockAmount = amount;
        AbstractPower pow = adp().getPower(IntelligencePower.POWER_ID);
        if (pow != null)
            blockAmount += amount2 * pow.amount;
        atb(new GainBlockAction(adp(), blockAmount));
    }

    @Override
    public void updateDescription() {
        // May need to adjust this
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
    }
}