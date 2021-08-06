package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theAquaLance.AquaLanceMod;
import theAquaLance.cards.AbstractEasyCard;

public class TsunamiAction extends AbstractGameAction {
    private static final float DURATION = 0.25F;
    private AbstractEasyCard card;

    public TsunamiAction(AbstractEasyCard card) {
        this.card = card;
        actionType = ActionType.DAMAGE;
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION) {
            card.applyPowers();
            card.allDmgTwoTop(AquaLanceMod.Enums.WATER);
        }

        tickDuration();
    }
}
