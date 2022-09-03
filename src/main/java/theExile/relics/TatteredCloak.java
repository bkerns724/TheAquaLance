package theExile.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import theExile.TheExile;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class TatteredCloak extends AbstractExileRelic {
    public static final String ID = makeID(TatteredCloak.class.getSimpleName());
    private static final int SHIVS_PER_COMBAT = 5;

    public TatteredCloak() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = SHIVS_PER_COMBAT;
        counter = amount;
        setUpdatedDescription();
    }

    @Override
    public void atBattleStartPreDraw() {
        counter = 5;
        grayscale = false;
    }

    @Override
    public void onManualDiscard() {
        if (counter > 0) {
            atb(new MakeTempCardInHandAction(new Shiv()));
            counter--;
            if (counter == 0)
                grayscale = true;
        }
    }
}
