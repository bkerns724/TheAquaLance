package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.powers.ChaoticMindPower;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class ChaoticMindAction extends AbstractGameAction {

    public ChaoticMindAction() {}

    @Override
    public void update() {
        AbstractPower pow = adp().getPower(ChaoticMindPower.POWER_ID);
        if (pow != null && pow.amount > 0) {
            att(new ReduceWithoutRemovePowerAction(pow));
            att(new DiscardAction(adp(), adp(), 1, false));
            att(new DrawCardAction(1));
        }
        isDone = true;
    }
}
