package theArcanist.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.TheArcanist;
import theArcanist.actions.ClownNoseAction;
import theArcanist.powers.ClownNosePower;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class ClownNose extends AbstractArcanistRelic implements OnApplyPowerRelic {
    public static final String ID = makeID(ClownNose.class.getSimpleName());
    public static final int DEBUFF_AMOUNT = 7;
    public static final int DAMAGE_AMOUNT = 66;

    public ClownNose() {
        super(ID, RelicTier.RARE,
                LandingSound.FLAT,
                TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = DEBUFF_AMOUNT;
        amount2 = DAMAGE_AMOUNT;
        setUpdatedDescription();
    }

    @Override
    public boolean onApplyPower(AbstractPower abstractPower, AbstractCreature target, AbstractCreature source) {
        if (target != adp() && !target.hasPower(ClownNosePower.POWER_ID)) {
            flash();
            atb(new RelicAboveCreatureAction(target, this));
            atb(new ClownNoseAction((AbstractMonster) target));
        }
        return true;
    }
}
