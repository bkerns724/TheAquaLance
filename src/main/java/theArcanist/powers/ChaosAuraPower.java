package theArcanist.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.ArcanistMod;
import theArcanist.actions.ChaosAuraAction;

import static theArcanist.util.Wiz.*;

public class ChaosAuraPower extends AbstractArcanistPower implements OnReceivePowerPower {
    public static String POWER_ID = ArcanistMod.makeID("ChaosAura");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int trueAmount;

    public ChaosAuraPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
        trueAmount = amount;
        this.amount = trueAmount / 3;
        amount2 = trueAmount % 3;
        isTwoAmount = true;
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (pow instanceof ChaosAuraPower) {
            trueAmount += ((ChaosAuraPower) pow).trueAmount;
            amount = trueAmount / 3;
            amount2 = trueAmount % 3;
        }
        return true;
    }

    @Override
    public void stackPower(int stackAmount) {
    }

    @Override
    public void atStartOfTurn() {
        forAllMonstersLiving(m -> atb(new ChaosAuraAction(m, trueAmount)));
    }
}