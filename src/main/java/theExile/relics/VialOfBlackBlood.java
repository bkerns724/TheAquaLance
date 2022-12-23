package theExile.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.TheExile;
import theExile.cards.AbstractExileCard;
import theExile.damagemods.EldritchDamage;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.ELDRITCH;
import static theExile.util.Wiz.adp;

public class VialOfBlackBlood extends AbstractExileRelic {
    public static final String ID = makeID(VialOfBlackBlood.class.getSimpleName());
    private static final float MULT = 2f;
    private static final CardStrings eldritchStrings = CardCrawlGame.languagePack.getCardStrings(EldritchDamage.ID);

    public VialOfBlackBlood() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    @Override
    public void setUpdatedDescription() {
        description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(eldritchStrings.DESCRIPTION, eldritchStrings.EXTENDED_DESCRIPTION[0]));
        initializeTips();
    }

    @Override
    public float atDamageModify(float damage, AbstractCard c) {
        if (c instanceof AbstractExileCard) {
            AbstractExileCard card = (AbstractExileCard) c;
            if (card.damageModList.contains(ELDRITCH))
                damage *= MULT;
        }
        return damage;
    }

    @Override
    public void onEquip() {
        for (AbstractCard card : adp().masterDeck.group) {
            if (card instanceof AbstractExileCard && ((AbstractExileCard) card).damageModList.contains(ELDRITCH))
                card.exhaust = true;
        }
    }

    @Override
    public void onPlayCard(AbstractCard targetCard, AbstractMonster m) {
        if (targetCard instanceof AbstractExileCard)
            if (((AbstractExileCard) targetCard).damageModList.contains(ELDRITCH))
                targetCard.exhaust = true;
    }
}
