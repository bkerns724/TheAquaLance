package theExile.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theExile.TheExile;
import theExile.powers.JinxPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.atb;

public class TwistedShuriken extends AbstractExileRelic {
    public static final String ID = makeID(TwistedShuriken.class.getSimpleName());
    private static final int ATTACKS_TO_TRIGGER = 3;
    private static final int DEBUFF_AMOUNT = 2;

    public TwistedShuriken() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        amount = ATTACKS_TO_TRIGGER;
        amount2 = DEBUFF_AMOUNT;
        setUpdatedDescription();
    }

    public void atTurnStart() {
        counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            ++counter;
            if (counter % amount == 0) {
                counter = 0;
                flash();
                atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                Wiz.forAllMonstersLiving(mon -> applyToEnemy(mon, new JinxPower(mon, amount2)));
            }
        }
    }

    public void onVictory() {
        counter = -1;
    }
}
