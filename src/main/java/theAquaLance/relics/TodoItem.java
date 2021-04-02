package theAquaLance.relics;

import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheAquaLance.Enums.TURQUOISE_COLOR);
    }
}
