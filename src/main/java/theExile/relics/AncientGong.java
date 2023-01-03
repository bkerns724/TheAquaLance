package theExile.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.TheExile;
import theExile.cards.AbstractResonantCard;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

// Some code in AbstractResonantCard
public class AncientGong extends AbstractExileRelic {
    public static final String ID = makeID(AncientGong.class.getSimpleName());
    private static final float MAX_HP_PENALTY = 0.2f;

    public AncientGong() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    public void onCardDraw(AbstractCard card) {
        if (card instanceof AbstractResonantCard)
            card.setCostForTurn(0);
    }

    public void onEquip() {
        int loss = (int) (adp().maxHealth * MAX_HP_PENALTY);
        adp().decreaseMaxHealth(loss);
    }
}
