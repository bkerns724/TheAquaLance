package theArcanist.relics;

import theArcanist.TheArcanist;
import theArcanist.powers.JinxThornsPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class DarkClover extends AbstractArcanistRelic {
    public static final String ID = makeID(DarkClover.class.getSimpleName());
    public static final int JINX_THORNS_AMOUNT = 1;

    public DarkClover() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = JINX_THORNS_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void atBattleStart() {
        applyToSelf(new JinxThornsPower(adp(), JINX_THORNS_AMOUNT));
    }
}
