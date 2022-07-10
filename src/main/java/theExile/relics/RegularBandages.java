package theExile.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import theExile.TheExile;

import static theExile.ExileMod.makeID;

import static theExile.util.Wiz.*;

public class RegularBandages extends AbstractExileRelic {
    public static final String ID = makeID(RegularBandages.class.getSimpleName());
    private static final int BLOCK_AMT = 2;

    public RegularBandages() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = BLOCK_AMT;
        setUpdatedDescription();
    }

    @Override
    public void onManualDiscard() {
        flash();
        atb(new RelicAboveCreatureAction(adp(), this));
        atb(new GainBlockAction(adp(), BLOCK_AMT));
    }
}
