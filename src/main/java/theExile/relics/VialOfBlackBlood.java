package theExile.relics;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.TheExile;
import theExile.cards.AbstractExileCard;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class VialOfBlackBlood extends AbstractExileRelic {
    public static final String ID = makeID(VialOfBlackBlood.class.getSimpleName());
    public static final int SELF_DAMAGE = 2;

    public VialOfBlackBlood() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheExile.Enums.EXILE_BLARPLE_COLOR);
        amount = SELF_DAMAGE;
        setUpdatedDescription();
    }

    @Override
    public float atDamageModify(float damage, AbstractCard c) {
        if (c instanceof AbstractExileCard) {
            AbstractExileCard card = (AbstractExileCard) c;
            if (card.damageModList.contains(AbstractExileCard.elenum.LIGHTNING))
                damage *= 2;
            if (card.damageModList.contains(AbstractExileCard.elenum.FIRE))
                damage *= 2;
        }
        return damage;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard instanceof AbstractExileCard) {
            AbstractExileCard card = (AbstractExileCard) targetCard;
            if (card.damageModList.contains(AbstractExileCard.elenum.LIGHTNING))
                atb(new LoseHPAction(adp(), adp(), SELF_DAMAGE));
            if (card.damageModList.contains(AbstractExileCard.elenum.FIRE))
                atb(new LoseHPAction(adp(), adp(), SELF_DAMAGE));
        }
    }
}
