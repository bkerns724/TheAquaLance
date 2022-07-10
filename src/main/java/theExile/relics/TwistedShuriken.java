package theExile.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theExile.TheExile;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class TwistedShuriken extends AbstractExileRelic {
    public static final String ID = makeID("TwistedShuriken");
    public static final int LOSE_STR_AMT = 1;
    public static final int NUM_CARDS = 3;

    public TwistedShuriken() {
        super(ID, AbstractRelic.RelicTier.UNCOMMON,
                AbstractRelic.LandingSound.CLINK,
                TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = LOSE_STR_AMT;
        amount2 = NUM_CARDS;
        setUpdatedDescription();
    }

    public void atTurnStart() {
        counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            ++counter;
            if (counter % 3 == 0) {
                counter = 0;
                flash();

                forAllMonstersLiving(m -> {
                    atb(new RelicAboveCreatureAction(m, this));
                    applyToEnemy(m, new StrengthPower(m, -LOSE_STR_AMT));
                });
            }
        }
    }

    public void onVictory() {
        counter = -1;
    }
}
