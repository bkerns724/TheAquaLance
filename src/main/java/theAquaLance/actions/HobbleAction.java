package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.powers.HobbledPower;

import static theAquaLance.util.Wiz.*;

public class HobbleAction extends AbstractGameAction {
    private AbstractMonster m;

    public HobbleAction(AbstractMonster m) {
        this.m = m;
        actionType = ActionType.REDUCE_POWER;
    }

    public void update() {
        if (m == null) {
            isDone = true;
            return;
        }
        AbstractPower hobPow = m.getPower(HobbledPower.POWER_ID);
        if (hobPow != null) {
            hobPow.flash();
            att(new TextAboveCreatureAction(adp(), ApplyPowerAction.TEXT[0]));
            CardCrawlGame.sound.play("NULLIFY_SFX");
            att(new ReducePowerAction(m, m, hobPow, 1));
        }
        isDone = true;
    }
}
