package theArcanist.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theArcanist.cards.AbstractArcanistCard;

import static theArcanist.ArcanistMod.makeID;

public class MagicNumberThree extends DynamicVariable {

    @Override
    public String key() {
        return makeID("M3");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractArcanistCard) {
            return ((AbstractArcanistCard) card).isSecondMagicModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractArcanistCard) {
            return ((AbstractArcanistCard) card).secondMagic;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractArcanistCard) {
            ((AbstractArcanistCard) card).isSecondMagicModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractArcanistCard) {
            return ((AbstractArcanistCard) card).baseSecondMagic;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractArcanistCard) {
            return ((AbstractArcanistCard) card).upgradedSecondMagic;
        }
        return false;
    }
}