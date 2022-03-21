package theArcanist.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import theArcanist.ArcanistMod;
import theArcanist.relics.PaperKat;

import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.att;

public class JinxPower extends AbstractArcanistPower implements OnReceivePowerPower {
    public static String POWER_ID = ArcanistMod.makeID(JinxPower.class.getSimpleName());

    public JinxPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (pow.ID.equals(GainStrengthPower.POWER_ID) || pow.type != PowerType.DEBUFF)
            return true;
        int lossMultiplier = 1;
        if (target.hasPower(BanePower.POWER_ID))
            lossMultiplier += target.getPower(BanePower.POWER_ID).amount;
        if (adp().hasRelic(PaperKat.ID))
            lossMultiplier++;
        att(new LoseHPAction(target, source, amount*lossMultiplier));
        return true;
    }
}