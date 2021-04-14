package theAquaLance.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class VialOfMineralWater extends AbstractEasyRelic {
    public static final String ID = makeID("VialOfMineralWater");
    private static final int NUM_TURNS = 2;
    private static final int DRAW_AMT = 1;

    public VialOfMineralWater() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    public void onEquip() {
        counter = 0;
    }

    public void atTurnStart() {
        if (counter == -1) // should never happen
            counter += 2;
        else
            ++counter;

        if (counter == NUM_TURNS) {
            counter = 0;
            flash();
            atb(new RelicAboveCreatureAction(adp(), this));
            atb(new DrawCardAction(DRAW_AMT));
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + NUM_TURNS + DESCRIPTIONS[1];
    }
}