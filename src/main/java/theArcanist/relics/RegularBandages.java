package theArcanist.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class RegularBandages extends AbstractArcanistRelic {
    public static final String ID = makeID(RegularBandages.class.getSimpleName());
    private static final int BLOCK_AMT = 2;

    public RegularBandages() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = BLOCK_AMT;
        setUpdatedDescription();
    }

    @Override
    public void onManualDiscard() {
        atb(new GainBlockAction(adp(), BLOCK_AMT));
    }
}
