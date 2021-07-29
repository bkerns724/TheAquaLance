package theAquaLance.relics;

import theAquaLance.TheAquaLance;
import theAquaLance.powers.IntelligencePower;

import static theAquaLance.AquaLanceMod.makeID;

import static theAquaLance.util.Wiz.*;

public class Glasses extends AbstractEasyRelic {
    public static final String ID = makeID("Glasses");
    private static final int INT_AMOUNT = 1;

    public Glasses() {
        super(ID, RelicTier.COMMON,
                LandingSound.MAGICAL,
                TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    @Override
    public void atBattleStart() {
        applyToSelf(new IntelligencePower(adp(), INT_AMOUNT));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + INT_AMOUNT + DESCRIPTIONS[1];
    }
}
