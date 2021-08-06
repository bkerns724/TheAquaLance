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
    public int mult;

    public IceArmorPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        mult = 0;
        amount2 = amount;
        canGoNegative2 = false;
    }

    @Override
    public void update(int slot) {
        super.update(slot);
        int temp = amount;
        AbstractPower pow = adp().getPower(IntelligencePower.POWER_ID);
        if (pow != null)
            temp += mult * pow.amount;
        if (temp != amount2) {
            amount2 = temp;
            updateDescription();
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        amount2 = amount;
        AbstractPower pow = adp().getPower(IntelligencePower.POWER_ID);
        if (pow != null)
            amount2 += mult * pow.amount;
        atb(new GainBlockAction(adp(), amount2));
    }

    @Override
    public void updateDescription() {
        amount2 = amount;
        AbstractPower pow = adp().getPower(IntelligencePower.POWER_ID);
        if (pow != null)
            amount2 += (mult * pow.amount)/6;
        description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1];
    }
}