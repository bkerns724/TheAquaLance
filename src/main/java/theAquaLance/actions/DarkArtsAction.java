package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theAquaLance.AquaLanceMod;

import static theAquaLance.util.Wiz.*;

public class DarkArtsAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private int secondDamage;
    private AbstractMonster m;

    public DarkArtsAction(AbstractMonster m, int secondDamage, boolean freeToPlayOnce) {
        this.m = m;
        this.secondDamage = secondDamage;
        this.freeToPlayOnce = freeToPlayOnce;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.DAMAGE;
    }

    public void update() {
        if (adp().hasRelic("Chemical X"))
            adp().getRelic("Chemical X").flash();

        atb(new DamageAction(m, new DamageInfo(adp(), secondDamage, AquaLanceMod.Enums.MAGIC), AquaLanceMod.Enums.BLOOD));

        if (!this.freeToPlayOnce) {
            adp().energy.use(EnergyPanel.totalCount);
        }

        this.isDone = true;
    }
}
