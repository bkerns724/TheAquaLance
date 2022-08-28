package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.powers.AnticipatePower;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class AnticipateAction extends AbstractGameAction {

    public AnticipateAction() {}

    @Override
    public void update() {
        AbstractPower pow = adp().getPower(AnticipatePower.POWER_ID);
        if (pow != null && pow.amount > 0) {
            att(new ReducePowerAction(adp(), adp(), pow, 1));
            att(new DiscardAction(adp(), adp(), 1, false));
            att(new DrawCardAction(1));
        }
        isDone = true;
    }
}
