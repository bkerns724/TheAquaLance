package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theAquaLance.cards.AbstractEmbedCard;
import theAquaLance.cards.ChargedShard;

import static theAquaLance.util.Wiz.adp;
import static theAquaLance.util.Wiz.att;

public class ShardXAction extends AbstractGameAction {
    private static final float DURATION = 0.5F;
    private boolean freeToPlay;
    private int energyOnUse;
    private ChargedShard card;
    private int energyMult;

    public ShardXAction(boolean freeToPlay, int energyOnUse, ChargedShard card, int energyMult) {
        this.freeToPlay = freeToPlay;
        this.energyOnUse = energyOnUse;
        this.card = card;
        this.energyMult = energyMult;
        actionType = ActionType.CARD_MANIPULATION;
        duration = DURATION;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1)
            effect = energyOnUse;

        if (adp().hasRelic("Chemical X")) {
            effect += 2;
            adp().getRelic("Chemical X").flash();
        }

        if (effect < 0)
            effect = 0;

        card.attuned = effect*energyMult;

        if (!freeToPlay)
            adp().energy.use(EnergyPanel.totalCount);

        isDone =true;
    }
}
