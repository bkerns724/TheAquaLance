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

    public HobbleAction() {
        actionType = ActionType.DIALOG;
    }

    public void update() {
        att(new TextAboveCreatureAction(adp(), ApplyPowerAction.TEXT[0]));
        CardCrawlGame.sound.play("NULLIFY_SFX");
        isDone = true;
    }
}
