package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.powers.JinxPower;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToEnemy;

public class GatheredPowerAction extends AbstractGameAction {
    private final boolean upgraded;
    private final boolean freeToPlayOnce;
    private final int energyOnUse;

    public GatheredPowerAction(AbstractMonster monster, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
        target = monster;
        this.upgraded = upgraded;
        this.freeToPlayOnce = freeToPlayOnce;
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

        if (upgraded)
            ++effect;

        if (effect > 0) {
            applyToEnemy(target, new JinxPower(target, effect*2));
            if (!freeToPlayOnce)
                adp().energy.use(EnergyPanel.totalCount);
        }

        isDone = true;
    }
}
