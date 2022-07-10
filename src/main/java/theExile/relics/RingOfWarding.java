package theExile.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.TheExile;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class RingOfWarding extends AbstractExileRelic {
    public static final String ID = makeID(RingOfWarding.class.getSimpleName());
    private static final int PROTECTION_AMOUNT = 3;

    public RingOfWarding() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = PROTECTION_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void atBattleStartPreDraw() {
        grayscale = false;
        counter = amount;
    }

    @Override
    public void onVictory() {
        grayscale = false;
        counter = amount;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.color == AbstractCard.CardColor.CURSE && counter >= 1) {
            flash();
            atb(new RelicAboveCreatureAction(adp(), this));
            cardDraw(1);
            counter--;
            if (counter <= 0)
                grayscale = true;
        }
    }
}
