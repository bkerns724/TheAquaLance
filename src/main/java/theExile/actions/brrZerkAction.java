package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.cards.BrrZerk;

import static theExile.util.Wiz.adp;

public class brrZerkAction extends AbstractGameAction {
    private final BrrZerk card;
    private final boolean free;
    private final int energyOnUse;


    public brrZerkAction(AbstractMonster m, BrrZerk card, boolean freeToPlayOnce, int energyOnUse) {
        this.card = card;
        target = m;
        duration = startDuration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.DAMAGE;
        free = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1)
            effect = energyOnUse;

        if (adp().hasRelic("Chemical X")) {
            effect += 2;
            adp().getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            for(int i = 0; i < effect; ++i)
                card.dmgTop((AbstractMonster) target);

            if (!free)
                adp().energy.use(EnergyPanel.totalCount);
        }

        isDone = true;
    }
}
