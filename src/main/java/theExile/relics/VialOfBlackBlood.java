package theExile.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.TheExile;
import theExile.cards.AbstractExileCard;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.LIGHTNING;

public class VialOfBlackBlood extends AbstractExileRelic {
    public static final String ID = makeID(VialOfBlackBlood.class.getSimpleName());

    public VialOfBlackBlood() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    @Override
    public float atDamageModify(float damage, AbstractCard c) {
        if (c instanceof AbstractExileCard) {
            AbstractExileCard card = (AbstractExileCard) c;
            if (card.damageModList.contains(LIGHTNING))
                damage *= 2;
        }
        return damage;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard instanceof AbstractExileCard)
            if (((AbstractExileCard) targetCard).damageModList.contains(LIGHTNING))
                targetCard.exhaust = true;
    }
}
