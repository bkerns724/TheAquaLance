package theAquaLance.relics;

import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SteamOrb extends AbstractEasyRelic {
    public static final String ID = makeID("SteamOrb");

    public SteamOrb() {
        super(ID, RelicTier.BOSS, LandingSound.SOLID, TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    public void onEquip() {
        ++adp().energy.energyMaster;
    }

    public void onUnequip() {
        --adp().energy.energyMaster;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    // Some code in DiscardActionPatch
}