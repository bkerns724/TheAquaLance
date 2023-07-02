package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class TinglingPower extends AbstractExilePower implements OnReceivePowerPower {
    public static String POWER_ID = makeID(TinglingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TinglingPower(int amount, int amount2) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
        isTwoAmount = true;
        this.amount = amount;
        this.amount2 = amount2;
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (target == owner && pow instanceof TinglingPower)
            amount2 += ((TinglingPower) pow).amount2;

        return true;
    }

    @Override
    public void atStartOfTurn() {
        atb(new GainEnergyAction(amount));
        atb(new LoseHPAction(adp(), adp(), amount2, ExileMod.Enums.BLOOD));
    }
}