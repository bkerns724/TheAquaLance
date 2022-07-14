package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theExile.util.Wiz.*;

public class TwistedFormAction extends AbstractGameAction {
    private int strengthLoss;
    private int healthLoss;

    public TwistedFormAction(AbstractCreature target, int strengthLoss, int healthLoss) {
        this.target = target;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        this.healthLoss = healthLoss;
        this.strengthLoss = strengthLoss;
    }

    @Override
    public void update() {
        if (duration == startDuration) {
            att(new LoseHPAction(target, adp(), healthLoss));
            CardCrawlGame.sound.play("POWER_CONSTRICTED");
            CardCrawlGame.sound.play("POWER_CONSTRICTED");
            applyToEnemyTop(target, new StrengthPower(target, -strengthLoss));
        }
        tickDuration();
    }
}
