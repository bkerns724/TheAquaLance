package theAquaLance.relics;

import theAquaLance.TheAquaLance;
import theAquaLance.powers.HobbledPower;

import static theAquaLance.AquaLanceMod.makeID;

import static theAquaLance.util.Wiz.*;

public class BagOfIceCubes extends AbstractEasyRelic {
    public static final String ID = makeID("BagOfIceCubes");
    private static final int HOBBLE_AMOUNT = 1;

    public BagOfIceCubes() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    @Override
    public void atBattleStart() {
        forAllMonstersLiving((m) -> applyToEnemy(m, new HobbledPower(m, HOBBLE_AMOUNT)));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HOBBLE_AMOUNT + DESCRIPTIONS[1];
    }
}