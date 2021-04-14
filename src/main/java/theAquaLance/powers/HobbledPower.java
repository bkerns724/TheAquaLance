package theAquaLance.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;
import theAquaLance.AquaLanceMod;

import java.util.ArrayList;

import static theAquaLance.util.Wiz.*;

public class HobbledPower extends AbstractEasyPower implements BetterOnApplyPowerPower {
    public static final String POWER_ID = AquaLanceMod.makeID("Hobbled");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESC = powerStrings.DESCRIPTIONS;

    private final static ArrayList<String> POWER_LIST = new ArrayList<String>() {
        {
            add(WeakPower.POWER_ID);
            add(VulnerablePower.POWER_ID);
            add(FrailPower.POWER_ID);
            add(StrengthPower.POWER_ID);
            add(DexterityPower.POWER_ID);
            add(FocusPower.POWER_ID);
        }
    };

    public HobbledPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, true, owner, amount);
        this.name = NAME;
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESC[0] + amount + DESC[2];
        else
            description = DESC[0] + amount + DESC[1];
    }

    @Override
    public void atEndOfRound() {
        atb(new ReducePowerAction(owner, owner, POWER_ID, 1));
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == owner && POWER_LIST.contains(power.ID) ) {
            att(new ReducePowerAction(owner, owner, POWER_ID, 1));
            return false;
        }
        return true;
    }
}
