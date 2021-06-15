package theAquaLance.relics;

import theAquaLance.TheAquaLance;
import theAquaLance.powers.SoakedPower;

import static theAquaLance.AquaLanceMod.makeID;

import static theAquaLance.util.Wiz.*;

public class Waterskin extends AbstractEasyRelic {
    public static final String ID = makeID("Waterskin");
    private static final int SOAK_AMOUNT = 1;

    public Waterskin() {
        super(ID, RelicTier.COMMON, LandingSound.HEAVY, TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    @Override
    public void atBattleStart() {
        forAllMonstersLiving((m) -> applyToEnemy(m, new SoakedPower(m, SOAK_AMOUNT)));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + SOAK_AMOUNT + DESCRIPTIONS[1];
    }
}