package theArcanist.relics;

import theArcanist.TheArcanist;
import theArcanist.actions.MyAddTempHPAction;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class VialOfNectar extends AbstractArcanistRelic {
    public static final String ID = makeID("VialOfNectar");
    private static final int TEMP_HP_AMOUNT = 3;

    public VialOfNectar() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
    }

    @Override
    public void atBattleStart() {
        atb(new MyAddTempHPAction(adp(), adp(), TEMP_HP_AMOUNT));
    }
}
