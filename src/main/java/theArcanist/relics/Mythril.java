package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class Mythril extends AbstractArcanistRelic {
    public static final String ID = makeID("Mythril");
    private static final int BLOCK_AMT = 5;

    public Mythril() {
        super(ID, RelicTier.COMMON, LandingSound.HEAVY, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
    }

    @Override
    public void onPlayerEndTurn() {
        if (adp().hand.isEmpty())
            atb(new GainBlockAction(adp(), BLOCK_AMT));
    }
}
