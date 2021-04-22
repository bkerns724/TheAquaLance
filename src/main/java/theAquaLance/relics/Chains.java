package theAquaLance.relics;

import theAquaLance.TheAquaLance;
import theAquaLance.powers.DrowningPower;

import static theAquaLance.AquaLanceMod.makeID;

import static theAquaLance.util.Wiz.*;

public class Chains extends AbstractEasyRelic {
    public static final String ID = makeID("Chains");

    public Chains() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DrowningPower.RELIC_DECAY + DESCRIPTIONS[1] + DrowningPower.DECAY + DESCRIPTIONS[2];
    }
}