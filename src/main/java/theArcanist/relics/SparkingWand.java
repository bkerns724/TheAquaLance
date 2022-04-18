package theArcanist.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.TheArcanist;
import theArcanist.powers.ResonatingPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.discard;

public class SparkingWand extends AbstractArcanistRelic implements OnReceivePowerRelic {
    public static final String ID = makeID(SparkingWand.class.getSimpleName());
    private static final int CHARGES = 3;
    private static final int BOOST_AMOUNT = 1;

    public SparkingWand() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = BOOST_AMOUNT;
        amount2 = CHARGES;
        counter = -1;
        setUpdatedDescription();
    }

    @Override
    public void atPreBattle() {
        counter = CHARGES;
        grayscale = false;
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature) {
        if (grayscale || adp().hasPower(ResonatingPower.POWER_ID))
            return true;
        if (abstractPower instanceof ResonatingPower) {
            abstractPower.amount += BOOST_AMOUNT;
            discard(1);
            counter--;
            if (counter == 0)
                grayscale = true;
        }
        return true;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature source, int stackAmount) {
        if (grayscale)
            return stackAmount;
        AbstractPower pow = adp().getPower(ResonatingPower.POWER_ID);
        if (power instanceof ResonatingPower && pow != null) {
            discard(1);
            counter--;
            if (counter == 0)
                grayscale = true;
            pow.amount += BOOST_AMOUNT;
        }
        return stackAmount;
    }

    @Override
    public void onVictory() {
        counter = -1;
        grayscale = false;
    }
}
