package theArcanist.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.cardDraw;

public class RingOfWarding extends AbstractArcanistRelic {
    public static final String ID = makeID(RingOfWarding.class.getSimpleName());
    private static final int PROTECTION_AMOUNT = 3;

    public RingOfWarding() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = PROTECTION_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        grayscale = false;
        counter = amount;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.color == AbstractCard.CardColor.CURSE && counter >= 1) {
            cardDraw(amount);
            counter--;
            if (counter <= 0)
                grayscale = true;
        }
    }
}
