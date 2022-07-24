package theExile.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.TheExile;
import theExile.cards.AbstractResonantCard;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class MysticalKazoo extends AbstractExileRelic {
    public static final String ID = makeID(MysticalKazoo.class.getSimpleName());
    private static final int BLOCK_AMOUNT = 2;

    public MysticalKazoo() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, TheExile.Enums.EXILE_BLARPLE_COLOR);
        setUpdatedDescription();
        amount = BLOCK_AMOUNT;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard instanceof AbstractResonantCard)
            atb(new GainBlockAction(adp(), BLOCK_AMOUNT));
    }
}
