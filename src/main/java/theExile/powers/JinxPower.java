package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theExile.ExileMod;
import theExile.relics.HexedStaff;
import theExile.relics.PaperKat;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class JinxPower extends AbstractExilePower implements OnReceivePowerPower {
    public static String POWER_ID = ExileMod.makeID(JinxPower.class.getSimpleName());

    public JinxPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        if (adp().hasRelic(HexedStaff.ID)) {
            AbstractRelic staff = adp().getRelic(HexedStaff.ID);
            if (staff.counter == 0)
                return;
            staff.flash();
            this.amount += staff.counter;
        }
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (pow.ID.equals(GainStrengthPower.POWER_ID) || pow.type != PowerType.DEBUFF || pow.owner == source)
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