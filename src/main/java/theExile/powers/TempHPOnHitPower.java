package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

import static theExile.util.Wiz.*;

public class TempHPOnHitPower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(TempHPOnHitPower.class.getSimpleName());

    public TempHPOnHitPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type == DamageInfo.DamageType.NORMAL)
            att(new AddTemporaryHPAction(owner, owner, amount));
    }

    @Override
    public void atStartOfTurn() {
        atb(new RemoveSpecificPowerAction(adp(), adp(), this));
    }
}