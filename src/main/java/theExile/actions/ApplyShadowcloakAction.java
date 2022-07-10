package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.powers.ShadowcloakPower;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class ApplyShadowcloakAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private AbstractMonster m;
    private int energyOnUse;
    private int mult;

    public ApplyShadowcloakAction(boolean freeToPlayOnce, int energyOnUse, int mult) {
        this.mult = mult;
        this.freeToPlayOnce = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.POWER;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1)
            effect = energyOnUse;

        if (adp().hasRelic("Chemical X")) {
            effect += 2;
            adp().getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            applyToSelf(new ShadowcloakPower(adp(), effect*mult));
            if (!freeToPlayOnce)
                adp().energy.use(EnergyPanel.totalCount);
        }

        isDone = true;
    }
}
