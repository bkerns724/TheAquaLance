package theArcanist.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public interface OnStatusPowerInterface {
    boolean onApplyStatus(AbstractCreature source, AbstractCard c);

    void onNegatedStatus(AbstractCreature source, AbstractCard c);
}
