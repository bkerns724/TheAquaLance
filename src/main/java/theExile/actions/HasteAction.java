package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class HasteAction extends AbstractGameAction {
    private final int draw;

    public HasteAction(int draw) {
        this.draw = draw;
    }

    @Override
    public void update() {
        if (EnergyPanel.totalCount > 0) {
            adp().loseEnergy(1);
            att(new DrawCardAction(draw));
        }
        isDone = true;
    }
}
