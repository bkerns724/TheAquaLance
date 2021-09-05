package theAquaLance.actions;//

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.cards.AbstractEmbedCard;
import theAquaLance.powers.EmbedPower;

public class PopOneAction extends AbstractGameAction {
    private static final float DURATION = 0.1F;
    private AbstractEmbedCard card;

    public PopOneAction(AbstractCreature target, AbstractEmbedCard card) {
        this.card = card;
        this.target = target;
        actionType = ActionType.CARD_MANIPULATION;
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION) {
            EmbedPower pow = (EmbedPower) target.getPower(EmbedPower.POWER_ID);
            if (pow.cards.contains(card))
                pow.popCard(card);
        }

        tickDuration();
    }
}
