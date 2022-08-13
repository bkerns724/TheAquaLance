package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.ExileMod;

import static theExile.util.Wiz.*;

public class GolemFrostAction extends AbstractGameAction {
    private final int damage;
    public GolemFrostAction(int damage) {
        this.damage = damage;
    }

    @Override
    public void update() {
        if (EnergyPanel.totalCount > 0) {
            adp().energy.use(1);
            forAllMonstersLiving(mon -> thornDmgTop(mon, damage, ExileMod.Enums.ICE_M));
        }

        isDone = true;
    }
}
