package theAquaLance.relics;

import theAquaLance.TheAquaLance;
import theAquaLance.actions.TriggerEnemyMarkAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class VialOfMineralWater extends AbstractEasyRelic {
    public static final String ID = makeID("VialOfMineralWater");

    public VialOfMineralWater() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    @Override
    public void onShuffle() {
        forAllMonstersLiving(m -> atb(new TriggerEnemyMarkAction(m)));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}