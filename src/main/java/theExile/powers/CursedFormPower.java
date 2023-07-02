package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import theExile.patches.CursedEchoPatch;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemyTop;

public class CursedFormPower extends AbstractExilePower implements OnReceivePowerPower {
    public static String POWER_ID = makeID(CursedFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int counter;

    public CursedFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
        counter = amount - 1;
    }

    @Override
    public void atStartOfTurn() {
        counter = amount;
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (CursedEchoPatch.CursedEchoField.echo.get(pow))
            return true;
        if (counter > 0 && target == owner && pow.type == PowerType.DEBUFF && !(pow instanceof GainStrengthPower)) {
            CursedEchoPatch.CursedEchoField.echo.set(pow, true);
            applyToEnemyTop(target, pow);
            counter--;
        }
        return true;
    }
}