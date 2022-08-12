package theExile.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.cards.AbstractExileCard;

import static theExile.ExileMod.makeID;

public class ThirdMagicNumber extends DynamicVariable {

    @Override
    public String key() {
        return makeID("M3");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractExileCard) {
            return ((AbstractExileCard) card).isThirdMagicModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractExileCard) {
            return ((AbstractExileCard) card).thirdMagic;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractExileCard) {
            ((AbstractExileCard) card).isThirdMagicModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractExileCard) {
            return ((AbstractExileCard) card).baseThirdMagic;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractExileCard) {
            return ((AbstractExileCard) card).upgradedThirdMagic;
        }
        return false;
    }
}