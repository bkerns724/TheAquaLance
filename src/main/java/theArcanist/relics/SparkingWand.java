package theArcanist.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.TheArcanist;
import theArcanist.powers.ResonatingPower;

import static theArcanist.util.Wiz.*;
import static theArcanist.ArcanistMod.makeID;

public class SparkingWand extends AbstractArcanistRelic implements OnReceivePowerRelic {
    public static final String ID = makeID(SparkingWand.class.getSimpleName());
    private static final int BOOST_AMOUNT = 3;

    public SparkingWand() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = BOOST_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void atPreBattle() {
        grayscale = false;
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature) {
        return true;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature source, int stackAmount) {
        if (grayscale || !power.ID.equals(ResonatingPower.POWER_ID))
            return stackAmount;
        else {
            grayscale = true;
            return stackAmount + BOOST_AMOUNT;
        }
    }
}
