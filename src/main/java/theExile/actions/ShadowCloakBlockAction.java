package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import theExile.cards.Vanish;
import theExile.powers.ShadowcloakPower;

import static theExile.util.Wiz.*;

public class ShadowCloakBlockAction extends AbstractGameAction {
    private Vanish card;

    public ShadowCloakBlockAction(Vanish card) {
        duration = DEFAULT_DURATION;
        this.card = card;
    }

    @Override
    public void update() {
        int count = 0;
        if (adp().hasPower(ShadowcloakPower.POWER_ID))
            count = adp().getPower(ShadowcloakPower.POWER_ID).amount;
        if (count > 0) {
            card.applyPowers();
            att(new GainBlockAction(adp(), card.block));
        }
        isDone = true;
        tickDuration();
    }
}
