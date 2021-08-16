package theAquaLance.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class VialOfMineralWater extends AbstractEasyRelic {
    public static final String ID = makeID("VialOfMineralWater");
    private static final int DRAW_AMOUNT = 2;
    private boolean triggered = false;

    public VialOfMineralWater() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    @Override
    public void onShuffle() {
        if(!triggered) {
            atb(new DrawCardAction(DRAW_AMOUNT));
            flash();
            triggered = true;
        }
    }

    @Override
    public void atTurnStart() {
        triggered = false;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DRAW_AMOUNT + DESCRIPTIONS[1];
    }
}