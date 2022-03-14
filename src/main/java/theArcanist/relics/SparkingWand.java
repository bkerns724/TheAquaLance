package theArcanist.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import theArcanist.TheArcanist;
import theArcanist.powers.ResonatingPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class SparkingWand extends AbstractClickRelic {
    public static final String ID = makeID("SparkingWand");
    public static final String textureString = "arcanistmodResources/images/ui/WandButton.png";
    private static final int BOOST_PERCENT = 25;

    public SparkingWand() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR, textureString);
        amount = BOOST_PERCENT;
    }

    @Override
    public void atPreBattle() {
        grayscale = false;
    }

    @Override
    public void onVictory() {
        grayscale = false;
    }

    @Override
    public void buttonPress() {
        if (adp().hasPower(ResonatingPower.POWER_ID)) {
            CardCrawlGame.sound.play("UI_CLICK_1");
            flash();
            ResonatingPower power = (ResonatingPower) adp().getPower(ResonatingPower.POWER_ID);
            int boost = (int)(BOOST_PERCENT/100.0f * power.amount);
            applyToSelf(new ResonatingPower(boost+ResonatingPower.DEDUCTION,
                    false, false, false, false, 0, 0, 0, 0));
            grayscale = true;
        }
    }
}
