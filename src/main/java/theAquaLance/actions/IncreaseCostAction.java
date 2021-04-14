package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import java.util.Iterator;
import java.util.UUID;

public class IncreaseCostAction extends AbstractGameAction {
    UUID uuid;
    private AbstractCard card = null;

    public IncreaseCostAction(AbstractCard card) {
        this.card = card;
    }

    public IncreaseCostAction(UUID targetUUID, int amount) {
        uuid = targetUUID;
        this.amount = amount;
        duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (card == null) {
            Iterator var1 = GetAllInBattleInstances.get(uuid).iterator();

            while(var1.hasNext()) {
                AbstractCard c = (AbstractCard)var1.next();
                c.modifyCostForCombat(1);
            }
        } else {
            card.modifyCostForCombat(1);
        }

        isDone = true;
    }
}
