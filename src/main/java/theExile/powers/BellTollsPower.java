package theExile.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class BellTollsPower extends AbstractExilePower {
    public static String POWER_ID = makeID(BellTollsPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BellTollsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (adp() == null)
            return;
        AbstractPower pow = adp().getPower(ResonatingPower.POWER_ID);
        int count = pow != null ? pow.amount * amount : 0;
        if (count > 0) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    flashWithoutSound();
                    CardCrawlGame.sound.playA("BELL", -0.25f);
                    att(new ApplyPowerAction(owner, adp(), new RingingPower(owner, count), count, AttackEffect.NONE));
                    isDone = true;
                }
            });
        }
    }
}