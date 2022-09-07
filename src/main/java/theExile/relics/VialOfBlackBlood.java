package theExile.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.TheExile;
import theExile.cards.AbstractExileCard;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.ELDRITCH;
import static theExile.cards.AbstractExileCard.elenum.LIGHTNING;
import static theExile.util.Wiz.adp;

public class VialOfBlackBlood extends AbstractExileRelic {
    public static final String ID = makeID(VialOfBlackBlood.class.getSimpleName());
    private static final float MULT = 1.5f;

    public VialOfBlackBlood() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    @Override
    public float atDamageModify(float damage, AbstractCard c) {
        if (c instanceof AbstractExileCard) {
            AbstractExileCard card = (AbstractExileCard) c;
            if (card.damageModList.contains(LIGHTNING) || card.damageModList.contains(ELDRITCH))
                damage *= MULT;
        }
        return damage;
    }

    @Override
    public void onEquip() {
        for (AbstractCard card : adp().masterDeck.group) {
            if (card instanceof AbstractExileCard && (((AbstractExileCard) card).damageModList.contains(LIGHTNING)
                    || ((AbstractExileCard) card).damageModList.contains(ELDRITCH)))
                card.exhaust = true;
        }
    }

    @Override
    public void onPlayCard(AbstractCard targetCard, AbstractMonster m) {
        if (targetCard instanceof AbstractExileCard)
            if (((AbstractExileCard) targetCard).damageModList.contains(LIGHTNING)
                    || ((AbstractExileCard) targetCard).damageModList.contains(ELDRITCH))
                targetCard.exhaust = true;
    }
}
