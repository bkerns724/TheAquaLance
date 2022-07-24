package theExile.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import theExile.TheExile;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class TatteredCloak extends AbstractExileRelic {
    public static final String ID = makeID(TatteredCloak.class.getSimpleName());

    public TatteredCloak() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheExile.Enums.EXILE_BLARPLE_COLOR);
        setUpdatedDescription();
    }

    @Override
    public void onManualDiscard() {
        atb(new MakeTempCardInHandAction(new Shiv()));
    }
}
