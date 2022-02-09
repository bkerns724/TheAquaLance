package theArcanist.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class CursePower extends AbstractArcanistPower implements OnReceivePowerPower {
    public static String POWER_ID = ArcanistMod.makeID("Curse");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CursePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (!pow.ID.equals(GainStrengthPower.POWER_ID) && pow.type == PowerType.DEBUFF) {
            atb(new LoseHPAction(target, source, amount));
            flash();
        }
        return true;
    }

    @Override
    public void upDescription() {
        description = DESCRIPTIONS[0];
    }
}