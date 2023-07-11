package theExile.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class CorrodedPower extends AbstractExilePower {
    public static String POWER_ID = makeID(CorrodedPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CorrodedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
        priority = 50;
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType damageType) {
        return damageType == DamageInfo.DamageType.NORMAL ? damage + amount : damage;
    }

    @Override
    public void onInitialApplication() {
        AbstractPower pow = owner.getPower(EmpoweredStaffPower.POWER_ID);
        if (pow == null)
            return;
        int stickyPlus = pow.amount;
        if (amount < stickyPlus)
            stickyPlus = amount;
        ((EmpoweredStaffPower)pow).amount2 += stickyPlus;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        AbstractPower pow = owner.getPower(EmpoweredStaffPower.POWER_ID);
        if (pow == null)
            return;
        int stickyPlus = pow.amount;
        if (stackAmount < stickyPlus)
            stickyPlus = stackAmount;
        ((EmpoweredStaffPower)pow).amount2 += stickyPlus;
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL)
            return damageAmount;
        else
            return damageAmount + amount;
    }

    @Override
    public void atEndOfRound() {
        EmpoweredStaffPower pow = (EmpoweredStaffPower) owner.getPower(EmpoweredStaffPower.POWER_ID);
        if (pow == null)
            atb(new RemoveSpecificPowerAction(owner, owner, this));
        else if (amount > pow.amount2)
            atb(new ReducePowerAction(owner, owner, this, amount - pow.amount2));
    }
}