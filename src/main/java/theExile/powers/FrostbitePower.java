package theExile.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theExile.ExileMod;

import static theExile.util.Wiz.att;

public class FrostbitePower extends AbstractExilePower {
    public static String POWER_ID = ExileMod.makeID(FrostbitePower.class.getSimpleName());

    public FrostbitePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner == owner) {
            flash();
            if (!target.hasPower(PermafrostPower.POWER_ID))
                att(new ReducePowerAction(owner, owner, this, 1));
            att(new DamageAction(owner, new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS),
                    AbstractGameAction.AttackEffect.NONE, true));
        }
    }
}