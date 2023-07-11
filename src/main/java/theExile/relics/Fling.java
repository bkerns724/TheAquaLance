package theExile.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.TheExile;
import theExile.cards.AbstractExileCard;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class Fling extends AbstractExileRelic {
    public static final String ID = makeID(Fling.class.getSimpleName());

    public Fling() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    @Override
    public void onEquip() {
        for (AbstractCard c : adp().masterDeck.group) {
            System.out.println(c.name);
            if (c instanceof AbstractExileCard && ((AbstractExileCard) c).sigil) {
                System.out.println("beep");
                c.cost = 1;
                c.costForTurn = 1;
                ((AbstractExileCard) c).initTitle();
            }
        }
    }
}
