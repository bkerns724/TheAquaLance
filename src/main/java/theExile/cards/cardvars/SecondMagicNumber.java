package theExile.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.cards.AbstractExileCard;

import static theExile.ExileMod.makeID;

public class SecondMagicNumber extends DynamicVariable {

    @Override
    public String key() {
        return makeID("M2");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractExileCard) {
            return ((AbstractExileCard) card).isSecondMagicModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractExileCard) {
            return ((AbstractExileCard) card).secondMagic;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractExileCard) {
            ((AbstractExileCard) card).isSecondMagicModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractExileCard) {
            return ((AbstractExileCard) card).baseSecondMagic;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractExileCard) {
            return ((AbstractExileCard) card).upgradedSecondMagic;
        }
        return false;
    }
}