package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theArcanist.powers.BanePower;

import static theArcanist.util.Wiz.*;

public class ApplyBaneAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private AbstractMonster m;
    private int energyOnUse;
    private boolean upgraded = false;

    public ApplyBaneAction(AbstractMonster m, boolean freeToPlayOnce, int energyOnUse, boolean upgraded) {
        this.m = m;
        this.freeToPlayOnce = freeToPlayOnce;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.DEBUFF;
        this.energyOnUse = energyOnUse;
        this.upgraded = upgraded;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1)
            effect = energyOnUse;

        if (adp().hasRelic("Chemical X")) {
            effect += 2;
            adp().getRelic("Chemical X").flash();
        }

        if (upgraded)
            effect += 1;

        if (effect > 0) {
            applyToEnemy(m, new BanePower(m, effect));
            if (!freeToPlayOnce) {
                adp().energy.use(EnergyPanel.totalCount);
            }
        }

        isDone = true;
    }
}
