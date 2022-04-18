package theArcanist.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.TheArcanist;
import theArcanist.powers.ResonatingPower;

import static theArcanist.ArcanistMod.makeID;

public class SparkingWand extends AbstractArcanistRelic implements OnReceivePowerRelic {
    public static final String ID = makeID(SparkingWand.class.getSimpleName());
    private static final int BOOST_AMOUNT = 2;

    public SparkingWand() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = BOOST_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature) {
        if (grayscale)
            return true;
        if (abstractPower instanceof ResonatingPower) {
            abstractPower.amount += BOOST_AMOUNT;
            grayscale = true;
        }
        return true;
    }

    @Override
    public void onVictory() {
        grayscale = false;
    }
}
