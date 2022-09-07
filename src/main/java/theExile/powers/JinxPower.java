package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import theExile.ExileMod;
import theExile.actions.JinxLoseHPAction;
import theExile.relics.HexedStaff;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class JinxPower extends AbstractExilePower implements OnReceivePowerPower {
    public static String POWER_ID = ExileMod.makeID(JinxPower.class.getSimpleName());

    public JinxPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        if (pow.type != PowerType.DEBUFF || pow.owner == source || pow.ID.equals(GainStrengthPower.POWER_ID)
                || pow.ID.equals(POWER_ID) || target.hasPower(ArtifactPower.POWER_ID))
            return true;
        float lossMultiplier = 1;
        HexedStaff staff = (HexedStaff) adp().getRelic(HexedStaff.ID);
        if (staff != null)
            lossMultiplier += staff.counter * HexedStaff.BONUS_MULT;
        att(new JinxLoseHPAction(target, source, (int)(amount*lossMultiplier), (pow instanceof JinxPower)));
        return true;
    }
}